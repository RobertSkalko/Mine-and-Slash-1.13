package com.robertx22.onevent.entity;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.EntityUnitPacket;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnTrackEntity {

    @SubscribeEvent
    public static void onEntityTrack(PlayerEvent.StartTracking event) {

        Entity entity = event.getTarget();

        if (entity instanceof LivingEntity) {
            if (entity.isEntityEqual(event.getEntityPlayer()) == false) {
                entity.world.getProfiler()
                        .startSection(Ref.MODID + ":on_track_entity_send_capability");
                if (Load.hasUnit(entity)) {

                    MMORPG.sendToClient(new EntityUnitPacket(entity), (ServerPlayerEntity) event
                            .getEntityPlayer());
                }
                entity.world.getProfiler().endSection();

            }

        }

    }
}
