package com.robertx22.api.event_subs;

import com.robertx22.api.MineAndSlashEvents;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GiveExpSub {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onGiveExp(MineAndSlashEvents.GiveExpEvent event) {

        if (event.getResult().equals(Event.Result.ALLOW)) {

            if (event.isCanceled() == false) {

                if (event.experience > 0) {

                    EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
                    event.playerCapability.GiveExp(player, event.experience);

                }
            }

        }
    }
}