package com.robertx22.onevent.Item;

import com.robertx22.config.ModConfig;
import com.robertx22.config.non_mine_items.ConfigItem;
import com.robertx22.config.non_mine_items.ConfigItems;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnItemPickupGiveStatsWhitelist {

    @SubscribeEvent
    public static void onItemCraftAddStats(PlayerContainerEvent event) {

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

                GearItemData test = Gear.Load(stack);

                if (test == null) {

                    String reg = stack.getItem().getRegistryName().toString();
                    if (ConfigItems.INSTANCE.map.containsKey(reg)) {

                        ConfigItem config = ConfigItems.INSTANCE.map.get(reg);

                        EntityPlayer player = event.getEntityPlayer();

                        if (Load.hasUnit(player)) {
                            if (data == null) {
                                data = Load.Unit(player);
                            }

                            stack = config.create(stack, data);

                            event.getEntityPlayer().inventory.markDirty();

                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
