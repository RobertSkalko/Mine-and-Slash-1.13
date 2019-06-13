package com.robertx22.onevent.entity;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.testing.Watch;
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

        if (endata.needsToBeGivenStats() == false) {
            endata.getUnit().InitMobStats(); // give new stats to mob on spawn
            endata.forceRecalculateStats(entity);
        }

        if (endata != null) {

            PlayerEntity nearestPlayer = null;

            Watch map = new Watch();

            if (WorldUtils.isMapWorld(entity.world)) {
                map.print("map check ");

                Watch nearest = new Watch();
                nearestPlayer = PlayerUtils.findNearest(entity, 150F);
                nearest.print("150 finding nearest player took ");

                Watch nearest2 = new Watch();
                nearestPlayer = PlayerUtils.findNearest(entity, 70F);
                nearest2.print("70 finding nearest player took  ");

                if (nearestPlayer == null) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
            }

            if (endata.needsToBeGivenStats()) {
                Unit unit = Unit.Mob(entity, nearestPlayer);
                endata.forceSetUnit(unit);
            }

        }

    }

}
