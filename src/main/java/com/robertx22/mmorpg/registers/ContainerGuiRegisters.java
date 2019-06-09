package com.robertx22.mmorpg.registers;

import com.robertx22.blocks.gear_factory_station.GuiGearFactory;
import net.minecraft.client.gui.ScreenManager;

public class ContainerGuiRegisters {
    public static void reg() {

        ScreenManager.registerFactory(ContainerTypeRegisters.GEAR_FACTORY, GuiGearFactory::new);

    }

}
