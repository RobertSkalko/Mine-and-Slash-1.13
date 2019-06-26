package com.robertx22.onevent.entity;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.ServerWorld;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnMobSpawn {

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

        if (event.getWorld().isRemote) {
            return;
        }
        if (event.getEntity() instanceof PlayerEntity) {
            return;
        }

        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        LivingEntity entity = (LivingEntity) event.getEntity();

        UnitData endata = Load.Unit(entity);

        if (endata != null) {

            PlayerEntity nearestPlayer = null;

            if (WorldUtils.isMapWorld(entity.world)) {

                nearestPlayer = PlayerUtils.nearestPlayer((ServerWorld) entity.world, entity);

                if (nearestPlayer == null) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
            }

            if (endata.needsToBeGivenStats()) {
                Unit unit = Unit.Mob(entity, nearestPlayer);
                endata.forceSetUnit(unit);
            } else {
                if (endata.getUnit() == null) {
                    endata.setUnit(new Unit(), entity);
                }

                endata.getUnit().InitMobStats(); // give new stats to mob on spawn
                endata.forceRecalculateStats(entity);
            }
        }

    }

}
