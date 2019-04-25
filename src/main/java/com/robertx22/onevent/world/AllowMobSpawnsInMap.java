package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AllowMobSpawnsInMap {

    /**
     * allows mobs to spawn at all times, even daylight. Some mobs are blacklisted
     * like slimes or non mobs
     */
    @SubscribeEvent
    public static void onMobForceSpawn(LivingSpawnEvent.CheckSpawn event) {

	if (event.getWorld() instanceof World == false) {
	    System.out.println("not instance of world");
	    return;
	}

	EntityLivingBase en = event.getEntityLiving();

	if (en instanceof EntityMob) {

	    EntityMob mob = (EntityMob) en;

	    IWorldData data = Load.World((World) event.getWorld());

	    if (data.isMapWorld()) {

		if (en instanceof EntitySlime) {

		    // no
		} else {
		    IBlockState iblockstate = mob.world.getBlockState((new BlockPos(mob)).down());

		    if (!iblockstate.canEntitySpawn(mob)) {
			return;
		    }

		    if (mob.isNotColliding()) {
			event.setResult(Result.ALLOW);
		    }
		}
	    }

	}
    }
}
