package com.robertx22.onevent.world;

import java.util.HashMap;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.world.dimension.Dimension;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnWorldMinutePassed {

    static final int oneMinute = 1200; // 1200 1 minute

    public static HashMap<Dimension, Integer> datas = new HashMap<Dimension, Integer>();

    @SubscribeEvent
    public static void onMinutePassedUpdateWorldTime(TickEvent.WorldTickEvent event) {

	if (event.phase == Phase.END && event.side.equals(LogicalSide.SERVER)) {

	    Dimension dim = event.world.dimension;

	    if (datas.containsKey(dim)) {
		datas.put(dim, datas.get(dim) + 1);
	    } else {
		datas.put(dim, 1);
	    }

	    if (datas.get(dim) > oneMinute) {

		datas.put(dim, 0);

		IWorldData data = Load.World(event.world);

		if (data != null) {
		    data.passMinute(event.world);

		}

	    }
	}

    }

}
