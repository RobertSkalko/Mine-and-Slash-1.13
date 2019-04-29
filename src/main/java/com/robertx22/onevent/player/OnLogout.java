package com.robertx22.onevent.player;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

@Mod.EventBusSubscriber
public class OnLogout {

    @SubscribeEvent
    public static void onLogin(PlayerLoggedOutEvent event) {

        if (event.getPlayer().world.isRemote) {
            return;
        }

        IWorldData world = Load.World(event.getPlayer().world);

        if (world.isMapWorld()) {
            world.teleportPlayerBack(event.getPlayer());
        }

    }
}