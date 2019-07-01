package com.robertx22.onevent.item;

import com.robertx22.config.ModConfig;
import com.robertx22.config.compatible_items.ConfigItem;
import com.robertx22.config.compatible_items.ConfigItems;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnContainerCompatibleItem {

    @SubscribeEvent
    public static void onContainerCompatibleItem(PlayerContainerEvent event) {

        try {

            if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get() == false) {
                return;
            }
            if (event.getEntityPlayer().world.isRemote) {
                return;
            }

            UnitData data = null;

            for (ItemStack stack : event.getEntityPlayer().inventory.mainInventory) {

                if (stack.isEmpty()) {
                    continue;
                }

                // fast check for every item
                if (Gear.has(stack) == false) {

                    String reg = stack.getItem().getRegistryName().toString();
                    if (ConfigItems.INSTANCE.map.containsKey(reg)) {

                        ConfigItem config = ConfigItems.INSTANCE.map.get(reg);

                        PlayerEntity player = event.getEntityPlayer();

                        if (Load.hasUnit(player)) {
                            if (data == null) {
                                data = Load.Unit(player);
                            }

                            // slow check to make absolutely sure it doesnt have stats
                            GearItemData gear = Gear.Load(stack);
                            if (gear == null) {
                                stack = config.create(stack, data.getLevel());
                                event.getEntityPlayer().inventory.markDirty();
                            }
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
