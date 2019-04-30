package com.robertx22.dimensions;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.mmorpg.Ref;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Ref.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDimRegister {

    @SubscribeEvent
    public static void registerAllModDims(RegisterDimensionsEvent event) {

        for (IWP iwp : WorldProviders.All.values()) {
            iwp.setupModDim();
        }

    }
}
