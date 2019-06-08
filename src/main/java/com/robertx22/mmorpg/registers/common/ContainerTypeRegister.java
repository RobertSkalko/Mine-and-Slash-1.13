package com.robertx22.mmorpg.registers.common;

import com.robertx22.blocks.gear_factory_station.ContainerGearFactory;
import net.minecraft.inventory.container.ContainerType;

public class ContainerTypeRegister {

    public static void reg() {

        ContainerType.func_221505_a("", ContainerGearFactory::new);
    }
}
