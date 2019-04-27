package com.robertx22.dimensions;

import com.robertx22.db_lists.WorldProviders;

import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ModDimRegister {

    @SubscribeEvent
    public static void reg(RegisterDimensionsEvent event) {

	for (IWP iwp : WorldProviders.All.values()) {
	    iwp.setupModDim();
	}

    }
}
