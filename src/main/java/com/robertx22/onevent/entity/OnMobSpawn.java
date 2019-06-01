package com.robertx22.onevent.entity;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.PlayerUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnMobSpawn {

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

        if (!(event.getEntity() instanceof EntityLivingBase)) {
            return;
        }

        EntityLivingBase entity = (EntityLivingBase) event.getEntity();

        if (entity.world.isRemote) {
            return;
        }

        UnitData endata = Load.Unit(entity);

        if (endata.needsToBeGivenStats() == false) {
            endata.getUnit().InitMobStats(); // give new stats to mob on spawn
            endata.forceRecalculateStats(entity);
        }

        if (endata != null) {

            if (entity instanceof EntityPlayer) {

            } else {

                EntityPlayer nearestPlayer = PlayerUtils.findNearest(entity, 200F);

                if (nearestPlayer == null) {
                    //event.setCanceled(true);

                    event.setResult(Event.Result.DENY);
                } else {
                    if (endata.needsToBeGivenStats()) {
                        Unit unit = Unit.Mob(entity, nearestPlayer);
                        endata.forceSetUnit(unit);
                    }
                }
            }

        }
    }

}
