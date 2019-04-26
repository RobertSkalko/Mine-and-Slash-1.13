package com.robertx22.mmorpg.registers.common;

import com.robertx22.mmorpg.config.ClientContainer;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigRegister {

    public static void reg() {
	ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientContainer.clientSpec);
    }
}
