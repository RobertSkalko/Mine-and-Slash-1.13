package com.robertx22.onevent.Item;

import com.robertx22.items.bags.BaseBagItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@Mod.EventBusSubscriber
public class OnPickupInsertIntoBag {

    @SubscribeEvent
    public static void onPickupItem(EntityItemPickupEvent event) {

        ItemStack stack = event.getItem().getItem();

        for (int i = 0; i < event.getEntityPlayer().inventory.getSizeInventory(); i++) {
            if (i == event.getEntityPlayer().inventory.currentItem)
                continue; // prevent item deletion

            ItemStack bag = event.getEntityPlayer().inventory.getStackInSlot(i);
            if (!bag.isEmpty() && bag.getItem() instanceof BaseBagItem) {
                IItemHandler bagInv = bag.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
                        .orElseGet(null);
                if (bagInv == null) {
                    return;
                }

                BaseBagItem bagitem = (BaseBagItem) bag.getItem();

                if (bagitem.IsValidItem(stack) && stack.getCount() > 0) {

                    for (int x = 0; x < bagInv.getSlots(); x++) {
                        ItemStack result = bagInv.insertItem(x, stack, false);
                        int numPickedUp = stack.getCount() - result.getCount();

                        event.getItem().setItem(result);

                        if (numPickedUp > 0) {
                            event.setCanceled(true);
                            if (!event.getItem().isSilent()) {
                                event.getItem().world.playSound(null, event.getEntityPlayer().posX, event
                                        .getEntityPlayer().posY, event.getEntityPlayer().posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((event
                                        .getItem().world.rand.nextFloat() - event.getItem().world.rand
                                        .nextFloat()) * 0.7F + 1.0F) * 2.0F);
                            }
                            ((EntityPlayerMP) event.getEntityPlayer()).connection.sendPacket(new SPacketCollectItem(event
                                    .getItem()
                                    .getEntityId(), event.getEntityPlayer()
                                    .getEntityId(), numPickedUp));
                            event.getEntityPlayer().openContainer.detectAndSendChanges();

                            return;
                        }
                    }
                }
            }
        }
    }
}
