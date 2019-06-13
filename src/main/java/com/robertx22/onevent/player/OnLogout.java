package com.robertx22.onevent.player;

import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

@EventBusSubscriber(Dist.DEDICATED_SERVER)
public class OnLogout {

    // maps either are random or may get deleted either in registry or the folder to reset the zone, players can't stay in maps
    @SubscribeEvent
    public static void onLogout(PlayerLoggedOutEvent event) {

        if (event.getPlayer().world.isRemote) {
            return;
        }

        if (WorldUtils.isMapWorldClass(event.getPlayer().world)) {
            Load.playerMapData(event.getPlayer()).teleportPlayerBack(event.getPlayer());
        }

    }
}