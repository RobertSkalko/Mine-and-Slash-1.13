package com.robertx22.onevent.world;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CheckWorldDelete {

    public static HashMap<String, Integer> idAndDeleteAttempts = new HashMap<String, Integer>();

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {

	if (!event.getWorld().isRemote() && event.getWorld().getDimension() != null) {

	    try {
		resetCount(event.getWorld().getDimension().getType().getRegistryName().toString());
	    } catch (Exception e1) {
		e1.printStackTrace();
	    }

	    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

	    Runnable noteThread = new Runnable() {
		@Override
		public void run() {

		    String id = "";

		    try {

			World world = (World) event.getWorld();

			id = world.dimension.toString();

			if (Load.World(world) != null) {
			    IWorldData data = Load.World(world);

			    if (data != null && data.isSetForDelete()) {

				/*
				 * MapDatas mapdata = (MapDatas)
				 * world.getMapStorage().getOrLoadData(MapDatas.class, MapDatas.getLoc());
				 * 
				 * if (mapdata != null) {
				 * 
				 * mapdata.delete(world.provider.getDimension()); increaseCount(id);
				 * 
				 * FileUtils.deleteDirectory(WorldFileUtils.getWorldDirectory(world));
				 * System.out.println("Deleting a temporary map world to free up disk space!");
				 * 
				 * }
				 */

			    }

			}
			scheduler.shutdown();

		    } catch (Exception e) {
			// keep scheduling until it's correctly deleted

			if (hasMoreTries(id)) {
			    scheduler.schedule(this, 3, TimeUnit.SECONDS);
			}
		    }
		}

	    };
	    scheduler.schedule(noteThread, 3, TimeUnit.SECONDS);
	}
    }

    private static boolean hasMoreTries(String id) {
	return idAndDeleteAttempts.containsKey(id) == false || idAndDeleteAttempts.get(id) < 5;
    }

    private static void resetCount(String id) {
	if (idAndDeleteAttempts.containsKey(id)) {
	    idAndDeleteAttempts.put(id, 0);
	}
    }

    private static void increaseCount(String id) {

	if (idAndDeleteAttempts.containsKey(id)) {
	    idAndDeleteAttempts.put(id, idAndDeleteAttempts.get(id) + 1);
	} else {
	    idAndDeleteAttempts.put(id, 1);
	}
    }

}
