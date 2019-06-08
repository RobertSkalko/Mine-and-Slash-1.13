package com.robertx22.onevent.item;

import com.mmorpg_libraries.curios.CurioSlots;
import com.mmorpg_libraries.curios.MyCurioUtils;
import com.robertx22.items.bags.AutoSalvageBag;
import com.robertx22.uncommon.interfaces.ISalvagable;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OnPickUpSalvage {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPickupItem(EntityItemPickupEvent event) {

        if (event.getEntityPlayer() != null) {

            PlayerEntity player = event.getEntityPlayer();

            if (!player.world.isRemote) {

                ItemStack bag = MyCurioUtils.getSlot(player, CurioSlots.SALVAGE_BAG);

                if (bag.getItem() instanceof AutoSalvageBag) {

                    ItemStack stack = event.getItem().getItem();
                    ISalvagable sal = AutoSalvageBag.getSalvagable(stack);

                    if (sal != null) {
                        AutoSalvageBag salvageBag = (AutoSalvageBag) bag.getItem();

                        if (salvageBag.shouldSalvageItem(sal, bag.getTag())) {

                            ItemStack result = salvageBag.getSalvageResultForItem(sal);
                            if (result.isEmpty() == false) {
                                stack.setCount(0);

                                ItemEntity enitem = new ItemEntity(player.world, player.posX, player.posY, player.posZ, result);
                                enitem.setNoPickupDelay();
                                player.world.spawnEntity(enitem);
                            } else {
                                // this shouldnt happen
                            }
                        }
                    }

                }

            }
        }
    }

}