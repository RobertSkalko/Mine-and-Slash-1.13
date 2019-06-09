package com.robertx22.mmorpg.registers;

import com.robertx22.blocks.gear_factory_station.ContainerGearFactory;
import com.robertx22.blocks.gear_factory_station.GuiGearFactory;
import com.robertx22.blocks.item_modify_station.ContainerGearModify;
import com.robertx22.blocks.item_modify_station.GuiGearModify;
import com.robertx22.blocks.map_device.ContainerMapDevice;
import com.robertx22.blocks.map_device.GuiMapDevice;
import com.robertx22.blocks.repair_station.ContainerGearRepair;
import com.robertx22.blocks.repair_station.GuiGearRepair;
import com.robertx22.blocks.salvage_station.ContainerGearSalvage;
import com.robertx22.blocks.salvage_station.GuiGearSalvage;
import net.minecraft.client.gui.ScreenManager;

public class ContainerGuiRegisters {

    public static void reg() {
        // BLACKED "OBSOLTE" PARTS ARE NEEDED OR IT ERROS !!!!
        ScreenManager.<ContainerGearFactory, GuiGearFactory>registerFactory(ContainerTypeRegisters.GEAR_FACTORY, GuiGearFactory::new);
        ScreenManager.<ContainerGearModify, GuiGearModify>registerFactory(ContainerTypeRegisters.GEAR_MODIFY, GuiGearModify::new);

        ScreenManager.<ContainerMapDevice, GuiMapDevice>registerFactory(ContainerTypeRegisters.MAP_DEVICE, GuiMapDevice::new);

        ScreenManager.<ContainerGearRepair, GuiGearRepair>registerFactory(ContainerTypeRegisters.GEAR_REPAIR, GuiGearRepair::new);
        ScreenManager.<ContainerGearSalvage, GuiGearSalvage>registerFactory(ContainerTypeRegisters.GEAR_SALVAGE, GuiGearSalvage::new);

        //    ScreenManager.registerFactory(ContainerTypeRegisters.LOOT_BAG, GuiLootBag::new);
        //   ScreenManager.registerFactory(ContainerTypeRegisters.MAP_BAG, GuiMapBag::new);
        //  ScreenManager.registerFactory(ContainerTypeRegisters.CURRENCY_BAG, GuiCurrencyBag::new);

    }

}
