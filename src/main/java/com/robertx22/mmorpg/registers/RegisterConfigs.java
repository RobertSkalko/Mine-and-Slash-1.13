package com.robertx22.mmorpg.registers;

import com.robertx22.mmorpg.config.ClientContainer;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class RegisterConfigs {

    public static void reg() {
	ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientContainer.clientSpec);
    }
}
