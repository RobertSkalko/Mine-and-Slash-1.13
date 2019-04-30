package com.robertx22.items.misc;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemIncreaseRarityNearestEntity extends Item {

    @ObjectHolder(Ref.MODID + ":increase_rarity_nearest_entity")
    public static final Item ITEM = null;

    public ItemIncreaseRarityNearestEntity() {

        super(new Properties().group(CreativeTabs.MyModTab).maxStackSize(64));

        RegisterItemUtils.RegisterItemName(this, "increase_rarity_nearest_entity");

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    EnumHand hand) {

        if (!world.isRemote) {
            try {

                AxisAlignedBB box = new AxisAlignedBB(player.getPosition()).grow(2);

                for (EntityLivingBase en : world.getEntitiesWithinAABB(EntityLivingBase.class, box)) {

                    if (en.isEntityEqual(player) == false && en instanceof EntityPlayer == false) {

                        UnitData data = Load.Unit(en);

                        if (data.increaseRarity(en)) {

                            player.getHeldItem(hand).shrink(1);

                            player.sendMessage(SLOC.chat("you_increased_rarity_entity"));

                            return new ActionResult<ItemStack>(EnumActionResult.PASS, player
                                    .getHeldItem(hand));
                        } else {
                            player.sendMessage(SLOC.chat("no_targets_found"));
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemIncreaseRarityNearestEntity());
    }

}