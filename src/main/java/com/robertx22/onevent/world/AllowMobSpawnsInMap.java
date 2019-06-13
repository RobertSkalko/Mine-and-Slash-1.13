package com.robertx22.onevent.world;

import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AllowMobSpawnsInMap {

    /**
     * TODO MAYBE SET MAPS TO BE NIGHT ONLY?
     * allows mobs to spawn at all times, even daylight. Some mobs are blacklisted
     * like slimes or non mobs
     */
    @SubscribeEvent
    public static void onMobForceSpawn(LivingSpawnEvent.CheckSpawn event) {

        if (RandomUtils.roll(20)) {

            LivingEntity en = event.getEntityLiving();

            if (en.world.isRemote) {
                return;
            }

            if (EntityTypeUtils.isMob(en)) {

                if (WorldUtils.isMapWorld(event.getWorld().getWorld())) {

                    if (en instanceof SlimeEntity) {
                        return;
                        // no
                    } else {

                        if (en.getPosition().getY() > 60) {

                            BlockState iblockstate = en.world.getBlockState((new BlockPos(en))
                                    .down()); // down might be problem

                            if (!iblockstate.canEntitySpawn(en.world, en.getPosition(), en
                                    .getType())) {
                                return;
                            }

                            event.setResult(Result.ALLOW);

                        }
                    }

                }
            }
        }

    }
}
