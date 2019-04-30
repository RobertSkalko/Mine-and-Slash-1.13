package com.robertx22.onevent.player;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnPlayerClone {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {

        if (!event.getEntityPlayer().world.isRemote) {

            EntityPlayer player = event.getEntityPlayer();

            Load.Unit(player).HandleCloneEvent(Load.Unit(event.getOriginal()));

        }

    }

}
