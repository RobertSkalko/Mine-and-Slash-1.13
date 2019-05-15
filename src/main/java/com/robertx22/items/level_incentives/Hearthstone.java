package com.robertx22.items.level_incentives;

import com.robertx22.Styles;
import com.robertx22.blocks.simple.AttunementBlock;
import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.dimensions.MapManager;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import com.robertx22.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        blocksTeleported = distanceTeleportedBlocks.get(rarity);
        totalUses = totalUsesList.get(rarity);
    }

    public static final List<Integer> levelReqs = Arrays.asList(1, 10, 25, 50, 75, 100);
    public static final List<Integer> activationTimes = Arrays.asList(10, 8, 7, 6, 5, 3);
    public static final List<Integer> distanceTeleportedBlocks = Arrays.asList(500, 1000, 2500, 5000, 15000, 100000);
    public static final List<Integer> totalUsesList = Arrays.asList(3, 10, 25, 50, 250, 1000);

    public Integer levelReq;
    public Integer activationTimeSeconds;
    public Integer blocksTeleported;
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

                        if (nbt.getInt("ticks") < tickRate + 1) {
                            entity.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, 0.5F, 1);
                        }

                        ParticleUtils.spawnParticles(Particles.HAPPY_VILLAGER, (EntityPlayer) entity, 15);

                    }

                } else {

                    NBTTagCompound nbt = stack.getTag();

                    if (nbt == null) {
                        nbt = new NBTTagCompound();
                        stack.setTag(nbt);
                    }

                    if (nbt.getBoolean("porting")) {

                        BlockPos pos = BlockPos.fromLong(nbt.getLong("pos"));

                        if (pos.distanceSq(entity.getPosition()) > 3) {

                            nbt.putBoolean("porting", false);
                            nbt.putInt("ticks", 0);

                            entity.sendMessage(SLOC.chat("teleport_canceled"));

                        } else {

                            if (nbt.contains("ticks")) {

                                ParticleUtils.spawnParticles(Particles.HEART, (EntityPlayer) entity, 10);

                                int ticks = nbt.getInt("ticks");
                                nbt.putInt("ticks", ticks + tickRate);

                                if (ticks > 20 * this.activationTimeSeconds) {

                                    nbt.putInt("ticks", 0);
                                    nbt.putBoolean("porting", false);

                                    teleportBack((EntityPlayer) entity, stack);

                                }
                            } else {
                                nbt.putInt("ticks", tickRate);
                            }

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void teleportBack(EntityPlayer player, ItemStack stack) {

        BlockPos pos = getLoc(stack);

        if (stack.hasTag() && stack.getTag().contains("dim") && pos != null) {
            pos = pos.up();
            String dim = stack.getTag().getString("dim");
            ResourceLocation res = new ResourceLocation(dim);
            DimensionType type = MapManager.getDimension(res);

            if (player.dimension != type) {
                player.changeDimension(type);
            }

            player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());

        } else {
            player.sendMessage(SLOC.chat("not_attuned"));

        }
    }

    public BlockPos getLoc(ItemStack stack) {

        if (stack.hasTag() && stack.getTag().contains("loc") && stack.getTag()
                .contains("dim")) {

            return BlockPos.fromLong(stack.getTag().getLong("loc"));
        }

        return null;
    }

    public void setLoc(ItemStack stack, BlockPos pos, DimensionType type) {

        ResourceLocation loc = DimensionType.getKey(type);

        if (loc != null) {
            stack.getTag().putLong("loc", pos.toLong());
            stack.getTag().putString("dim", loc.toString());
        }
    }

    public boolean distanceCanBeTeleported(EntityPlayer player, ItemStack stack) {

        BlockPos place = getLoc(stack);
        BlockPos current = player.getPosition();

        double distance = place.getDistance(current);

        if (distance < this.blocksTeleported) {
            return true;
        }

        return false;

    }

    public boolean hasLoc(ItemStack stack) {
        return getLoc(stack) != null;
    }

    @Mod.EventBusSubscriber
    public static class Event {

        @SubscribeEvent
        public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock evt) {
            if (evt.getSide().equals(LogicalSide.SERVER)) {

                EntityPlayerMP player = (EntityPlayerMP) evt.getEntityPlayer();
                IBlockState block = player.world.getBlockState(evt.getPos());

                ItemStack stack = evt.getItemStack();

                if (stack.getItem() instanceof Hearthstone == false) {
                    return;
                }

                Hearthstone item = (Hearthstone) evt.getItemStack().getItem();

                if (block.getBlock().equals(AttunementBlock.BLOCK)) {

                    DimensionType type = evt.getWorld().getDimension().getType();

                    item.setLoc(stack, new BlockPos(evt.getHitVec()), type);
                    player.sendMessage(SLOC.chat("attunement_set"));

                } else {
                    player.sendMessage(SLOC.chat("not_attunement_altar"));
                }
            }

        }

    }

    private int getRemainingUses(ItemStack stack) {

        if (stack.hasTag()) {
            NBTTagCompound nbt = stack.getTag();

            if (nbt.contains("uses")) {
                return nbt.getInt("uses");
            }
        }
        return this.totalUses;

    }

    private void decreaseUses(ItemStack stack) {

        NBTTagCompound nbt = stack.getTag();

        int left = this.totalUses;

        if (nbt.contains("uses")) {
            left = nbt.getInt("uses");
        }
        left--;

        nbt.putInt("uses", left);

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

        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(CLOC.word("distance")
                        .appendText(" " + this.blocksTeleported)
                        .appendText(" ")
                        .appendSibling(CLOC.word("blocks").appendText(". "))), tooltip);

        Tooltip.add(Styles.GREENCOMP()
                .appendSibling(CLOC.word("uses")
                        .appendText(": " + this.totalUses)
                        .appendText(" ")
                        .appendSibling(CLOC.word("left"))
                        .appendText(": " + this.getRemainingUses(stack))), tooltip);

        Tooltip.add(Styles.REDCOMP()
                .appendSibling(CLOC.word("activation_time")
                        .appendText(": " + this.activationTimeSeconds + " ")
                        .appendSibling(CLOC.word("seconds"))), tooltip);

        if (getLoc(stack) != null) {
            Tooltip.add(Styles.GOLDCOMP()
                    .appendSibling(CLOC.word("position")
                            .appendText(": " + locTooltip(stack))), tooltip);

            Tooltip.add(Styles.BLUECOMP()
                    .appendSibling(new TextComponentString(dimTooltip(stack))), tooltip);

        }

        Tooltip.add("", tooltip);

        tooltip.add(TooltipUtils.level(this.levelReq));

    }

    private String dimTooltip(ItemStack stack) {

        return stack.getTag().getString("dim");

    }

    private String locTooltip(ItemStack stack) {

        BlockPos pos = this.getLoc(stack);

        return "x: " + pos.getX() + " y: " + pos.getY() + " z: " + pos.getZ();

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    EnumHand hand) {

        if (world.isRemote == false) {

            ItemStack stack = player.getHeldItem(hand);

            if (stack.getItem() instanceof Hearthstone == false) {
                return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
            }

            Hearthstone item = (Hearthstone) stack.getItem();

            try {

                if (!stack.hasTag()) {
                    stack.setTag(new NBTTagCompound());
                }

                if (item.hasLoc(stack) == false) {
                    player.sendMessage(SLOC.chat("not_attuned"));
                    stack.getTag().putBoolean("porting", false);
                    return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
                }

                if (item.distanceCanBeTeleported(player, stack) == false) {
                    player.sendMessage(SLOC.chat("distance_warning"));
                    stack.getTag().putBoolean("porting", false);
                    return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
                }

                if (Load.Unit(player).getLevel() < item.levelReq) {
                    player.sendMessage(SLOC.chat("not_high_enough_level"));
                    stack.getTag().putBoolean("porting", false);
                    return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
                }

                item.decreaseUses(stack);

                stack.getTag().putBoolean("porting", true);
                stack.getTag().putLong("pos", player.getPosition().toLong());

                player.sendMessage(SLOC.chat("teleport_begin"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

}
