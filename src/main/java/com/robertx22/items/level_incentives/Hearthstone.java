package com.robertx22.items.level_incentives;

import com.robertx22.Styles;
import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.db_lists.Rarities;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import com.robertx22.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Hearthstone extends Item {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public Hearthstone(int rarity) {

        super(new Properties().group(CreativeTabs.MyModTab)
                .maxStackSize(1)
                .defaultMaxDamage(0));

        this.rarity = rarity;
        this.setup(rarity);
        RegisterItemUtils.RegisterItemName(this, "hearthstone/" + rarity);
    }

    int rarity;

    private void setup(int rarity) {
        levelReq = levelReqs.get(rarity);
        activationTimeSeconds = activationTimes.get(rarity);
        cooldownTimeMinute = cooldownTimeMinutes.get(rarity);
        totalUses = totalUsesList.get(rarity);
    }

    public static final List<Integer> levelReqs = Arrays.asList(1, 10, 25, 50, 75, 100);
    public static final List<Integer> activationTimes = Arrays.asList(10, 8, 7, 6, 5, 3);
    public static final List<Integer> cooldownTimeMinutes = Arrays.asList(60, 50, 40, 30, 20, 10);
    public static final List<Integer> totalUsesList = Arrays.asList(3, 10, 25, 50, 250, 1000);

    public Integer levelReq;
    public Integer activationTimeSeconds;
    public Integer cooldownTimeMinute;
    public Integer totalUses;

    public static final int tickRate = 20;

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entity, int itemSlot,
                              boolean isSelected) {

        try {
            if (entity.ticksExisted % tickRate == 0 && entity instanceof EntityPlayer) {

                if (worldIn.isRemote) {
                    NBTTagCompound nbt = stack.getTag();
                    if (nbt != null && nbt.getBoolean("porting")) {

                        ParticleUtils.spawnParticles(Particles.CRIT, (EntityPlayer) entity, 5);
                    }

                } else {

                    NBTTagCompound nbt = stack.getTag();

                    if (nbt == null) {
                        nbt = new NBTTagCompound();
                        stack.setTag(nbt);
                    }

                    decreaseCurrentCooldown(stack, tickRate);

                    if (nbt.getBoolean("porting")) {

                        BlockPos pos = BlockPos.fromLong(nbt.getLong("pos"));

                        if (pos.distanceSq(entity.getPosition()) > 3) {

                            nbt.setBoolean("porting", false);
                            nbt.setInt("ticks", 0);
                            this.setCooldownToZero(stack);

                            entity.sendMessage(SLOC.chat("teleport_canceled"));

                        } else {

                            if (nbt.hasKey("ticks")) {

                                ParticleUtils.spawnParticles(Particles.HEART, (EntityPlayer) entity, 10);

                                int ticks = nbt.getInt("ticks");
                                nbt.setInt("ticks", ticks + tickRate);

                                if (ticks > 20 * this.activationTimeSeconds) {

                                    nbt.setInt("ticks", 0);
                                    nbt.setBoolean("porting", false);

                                    teleportBack((EntityPlayer) entity);

                                }
                            } else {
                                nbt.setInt("ticks", tickRate);
                            }

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void teleportBack(EntityPlayer player) {

        BlockPos pos = player.getBedLocation(player.world.getDimension().getType());

        if (pos == null) {
            player.sendMessage(SLOC.chat("no_bed"));
        } else {
            player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());

        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    EnumHand hand) {

        if (!world.isRemote) {
            try {

                ItemStack stack = player.getHeldItem(hand);

                if (!stack.hasTag()) {
                    stack.setTag(new NBTTagCompound());
                }

                if (this.getCurrentCooldownMinutes(stack) > 0) {
                    player.sendMessage(SLOC.chat("cooldown_warning"));
                    stack.getTag().setBoolean("porting", false);
                    return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
                }

                if (Load.Unit(player).getLevel() < this.levelReq) {
                    player.sendMessage(SLOC.chat("not_high_enough_level"));
                    stack.getTag().setBoolean("porting", false);
                    return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
                }

                this.decreaseUses(stack);
                this.resetCooldown(stack);

                stack.getTag().setBoolean("porting", true);
                stack.getTag().setLong("pos", player.getPosition().toLong());

                player.sendMessage(SLOC.chat("teleport_begin"));

                return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    private int getRemainingUses(ItemStack stack) {

        if (stack.hasTag()) {
            NBTTagCompound nbt = stack.getTag();

            if (nbt.hasKey("uses")) {
                return nbt.getInt("uses");
            }
        }
        return this.totalUses;

    }

    private int getCurrentCooldownMinutes(ItemStack stack) {

        if (stack.hasTag()) {
            NBTTagCompound nbt = stack.getTag();

            if (nbt.hasKey("cooldown")) {
                return nbt.getInt("cooldown") / 20 / 60; // ticks;
            }
        }
        return 0;

    }

    private void resetCooldown(ItemStack stack) {

        NBTTagCompound nbt = stack.getTag();

        nbt.setInt("cooldown", this.cooldownTimeMinute * 20 * 60);

    }

    private void setCooldownToZero(ItemStack stack) {

        NBTTagCompound nbt = stack.getTag();

        nbt.setInt("cooldown", 0);

    }

    private void decreaseCurrentCooldown(ItemStack stack, int ticks) {

        NBTTagCompound nbt = stack.getTag();

        if (nbt.hasKey("cooldown")) {
            int left = nbt.getInt("cooldown");

            left -= ticks;

            if (left > -1) { // dont keep reducing bellow 0
                nbt.setInt("cooldown", left);
                stack.setTag(nbt);
            }
        }

    }

    private void decreaseUses(ItemStack stack) {

        NBTTagCompound nbt = stack.getTag();

        int left = this.totalUses;

        if (nbt.hasKey("uses")) {
            left = nbt.getInt("uses");
        }
        left--;

        nbt.setInt("uses", left);

        stack.setTag(nbt);

        if (left < 1) {
            stack.shrink(1);
        }

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        Tooltip.add("", tooltip);

        Tooltip.add(CLOC.word("cooldown")
                .appendText(" " + this.cooldownTimeMinute)
                .appendText(" ")
                .appendSibling(CLOC.word("minutes")
                        .appendText(". ")
                        .appendSibling(CLOC.word("left"))
                        .appendText(": " + this.getCurrentCooldownMinutes(stack)))
                .setStyle(Styles.BLUE), tooltip);

        Tooltip.add(CLOC.word("uses")
                .appendText(": " + this.totalUses)
                .appendText(" ")
                .appendSibling(CLOC.word("left"))
                .appendText(": " + this.getRemainingUses(stack))
                .setStyle(Styles.GREEN), tooltip);

        Tooltip.add(CLOC.word("activation_time")
                .appendText(": " + this.activationTimeSeconds + " ")
                .appendSibling(CLOC.word("seconds"))
                .setStyle(Styles.RED), tooltip);

        Tooltip.add("", tooltip);

        tooltip.add(TooltipUtils.level(this.levelReq));

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Rarities.Items.forEach((x) -> Items.put(x.Rank(), new Hearthstone(x.Rank())));
        Items.values().forEach((x) -> event.getRegistry().register(x));
    }

}
