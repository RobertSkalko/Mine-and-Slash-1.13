package com.robertx22.onevent.player;

import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnDeathInMap {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDeath(LivingDeathEvent evt) {

        LivingEntity living = evt.getEntityLiving();

        if (living.world.isRemote) {
            return;
        }

        if (living instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) living;

            if (WorldUtils.isMapWorld(living.world)) {
                PlayerMapData.IPlayerMapData data = Load.playerMapData(player);

                data.onPlayerDeath(player);
                evt.setCanceled(true);

            }

        }

    }
}
