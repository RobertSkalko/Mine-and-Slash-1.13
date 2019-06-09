package com.robertx22.mmorpg.registers;

import com.robertx22.blocks.gear_factory_station.GuiGearFactory;
import com.robertx22.blocks.item_modify_station.GuiGearModify;
import com.robertx22.blocks.map_device.GuiMapDevice;
import com.robertx22.blocks.repair_station.GuiGearRepair;
import com.robertx22.blocks.salvage_station.GuiGearSalvage;
import com.robertx22.items.bags.currency_bag.GuiCurrencyBag;
import com.robertx22.items.bags.loot_bag.GuiLootBag;
import com.robertx22.items.bags.map_bag.GuiMapBag;
import net.minecraft.client.gui.ScreenManager;

public class ContainerGuiRegisters {

    public static void reg() {

        ScreenManager.registerFactory(ContainerTypeRegisters.GEAR_FACTORY, GuiGearFactory::new);
        ScreenManager.registerFactory(ContainerTypeRegisters.GEAR_MODIFY, GuiGearModify::new);
        ScreenManager.registerFactory(ContainerTypeRegisters.GEAR_REPAIR, GuiGearRepair::new);
        ScreenManager.registerFactory(ContainerTypeRegisters.GEAR_SALVAGE, GuiGearSalvage::new);

        ScreenManager.registerFactory(ContainerTypeRegisters.MAP_DEVICE, GuiMapDevice::new);

        ScreenManager.registerFactory(ContainerTypeRegisters.LOOT_BAG, GuiLootBag::new);
        ScreenManager.registerFactory(ContainerTypeRegisters.MAP_BAG, GuiMapBag::new);
        ScreenManager.registerFactory(ContainerTypeRegisters.CURRENCY_BAG, GuiCurrencyBag::new);

    }

}
