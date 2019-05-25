package com.robertx22.onevent.player;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

@EventBusSubscriber
public class OnLogout {

    @SubscribeEvent
    public static void onLogin(PlayerLoggedOutEvent event) {

        if (event.getPlayer().world.isRemote) {
            return;
        }
/*
        IWorldData world = Load.World(event.getPlayer().world);

        if (world.isMapWorld()) {
            world.teleportPlayerBack(event.getPlayer());
        }


 */
    }
}