package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
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

        EntityLivingBase en = event.getEntityLiving();

        if (en instanceof IMob) {

            IWorldData data = Load.World(event.getWorld().getWorld());

            if (data.isMapWorld()) {

                if (en instanceof EntitySlime) {

                    // no
                } else {
                    IBlockState iblockstate = en.world.getBlockState((new BlockPos(en)).down());

                    if (!iblockstate.canEntitySpawn(en)) {
                        return;
                    }

                    event.setResult(Result.ALLOW);

                }
            }

        }
    }
}
