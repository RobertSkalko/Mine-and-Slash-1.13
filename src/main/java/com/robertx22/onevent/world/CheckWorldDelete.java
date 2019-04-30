package com.robertx22.onevent.world;

import com.robertx22.dimensions.MapManager;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CheckWorldDelete {

    public static HashMap<ResourceLocation, Integer> idAndDeleteAttempts = new HashMap<ResourceLocation, Integer>();

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {

        if (!event.getWorld().isRemote() && event.getWorld().getDimension() != null) {

            try {
                resetCount(event.getWorld().getDimension().getType().getRegistryName());
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            Runnable noteThread = new Runnable() {
                @Override
                public void run() {

                    ResourceLocation id = null;

                    try {

                        World world = (World) event.getWorld();

                        id = world.getDimension().getType().getRegistryName();

                        IWorldData data = Load.World(world);

                        if (data.isSetForDelete()) {
                            MapManager.unRegister(world);
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

    private static boolean hasMoreTries(ResourceLocation id) {
        return idAndDeleteAttempts.containsKey(id) == false || idAndDeleteAttempts.get(id) < 5;
    }

    private static void resetCount(ResourceLocation id) {
        if (idAndDeleteAttempts.containsKey(id)) {
            idAndDeleteAttempts.put(id, 0);
        }
    }

    private static void increaseCount(ResourceLocation id) {

        if (idAndDeleteAttempts.containsKey(id)) {
            idAndDeleteAttempts.put(id, idAndDeleteAttempts.get(id) + 1);
        } else {
            idAndDeleteAttempts.put(id, 1);
        }
    }

}
