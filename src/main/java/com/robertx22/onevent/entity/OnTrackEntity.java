package com.robertx22.onevent.entity;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.EntityUnitPacket;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnTrackEntity {

    @SubscribeEvent
    public static void onEntityTrack(PlayerEvent.StartTracking event) {

        try {
            if (event.getEntity().world.isRemote) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Entity entity = event.getTarget();

        if (entity instanceof EntityLivingBase) {
            if (entity.isEntityEqual(event.getEntityPlayer()) == false) {
                if (Load.hasUnit(entity)) {

                    MMORPG.sendToClient(new EntityUnitPacket(entity), (EntityPlayerMP) event
                            .getEntityPlayer());
                }

            }

        }

    }
}