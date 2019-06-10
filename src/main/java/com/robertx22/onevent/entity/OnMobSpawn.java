package com.robertx22.onevent.entity;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnMobSpawn {

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        LivingEntity entity = (LivingEntity) event.getEntity();

        if (entity.world.isRemote) {
            return;
        }

        UnitData endata = Load.Unit(entity);

        if (endata.needsToBeGivenStats() == false) {
            endata.getUnit().InitMobStats(); // give new stats to mob on spawn
            endata.forceRecalculateStats(entity);
        }

        if (endata != null) {

            if (entity instanceof PlayerEntity) {

            } else {

                PlayerEntity nearestPlayer = PlayerUtils.findNearest(entity, 150F);

                if (nearestPlayer == null) {
                    if (WorldUtils.isMapWorld(entity.world)) {
                        event.setResult(Event.Result.DENY);
                    }
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
