package com.robertx22.onevent.player;

import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.capability.WorldUtils;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnDeathInMap {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDeath(LivingDeathEvent evt) {

        EntityLivingBase living = evt.getEntityLiving();

        if (living instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) living;

            if (WorldUtils.isMapWorld(living.world)) {
                PlayerMapData.IPlayerMapData data = Load.playerMapData(player);

                data.onPlayerDeath(player);
                evt.setCanceled(true);

            }

        }

    }
}
