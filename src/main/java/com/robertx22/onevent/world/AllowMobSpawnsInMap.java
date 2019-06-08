package com.robertx22.onevent.world;

import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.IMob;
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

        LivingEntity en = event.getEntityLiving();

        if (en instanceof IMob) {

            if (WorldUtils.isMapWorld(event.getWorld().getWorld())) {

                if (en instanceof SlimeEntity) {
                    return;
                    // no
                } else {
                    if (EntityTypeUtils.isMob(en)) {

                        BlockState iblockstate = en.world.getBlockState((new BlockPos(en))
                                .down());

                        if (!iblockstate.canEntitySpawn(en)) {
                            return;
                        }

                        event.setResult(Result.ALLOW);

                    }

                }
            }

        }
    }
}
