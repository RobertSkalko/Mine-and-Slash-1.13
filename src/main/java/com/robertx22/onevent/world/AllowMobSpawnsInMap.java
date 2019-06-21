package com.robertx22.onevent.world;

import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.function.Predicate;

@EventBusSubscriber
public class AllowMobSpawnsInMap {

    /**
     * TODO MAYBE SET MAPS TO BE NIGHT ONLY?
     * allows mobs to spawn at all times, even daylight. Some mobs are blacklisted
     * like slimes or non mobs
     */
    @SubscribeEvent
    public static void onMobForceSpawn(LivingSpawnEvent.CheckSpawn event) {

        if (true) {
            return;
        }

        LivingEntity en = event.getEntityLiving();

        if (en.world.isRemote) {
            return;
        }
        if (en instanceof PlayerEntity) {
            return;
        }

        // Watch watch = new Watch().min(100);

        if (RandomUtils.roll(80)) {

            if (EntityTypeUtils.isMob(en)) {

                if (WorldUtils.isMapWorld(event.getWorld().getWorld())) {

                    if (possibleToSpawnMob(en)) {
                        event.setResult(Result.DENY);
                        en.world.addEntity(en); // i spawn it myself cus otherwise minecraft checks again things and sometimes doesnt spawn it..
                        return;
                    }

                }
            }
        }

        //watch.print("spawn ");

    }

    public static boolean possibleToSpawnMob(Entity en) {

        if (en instanceof SlimeEntity) {
            return false;
            // no
        } else {

            if (isNearSurface(en.getPosition(), en.world, 5)) {

                BlockState iblockstate = en.world.getBlockState((new BlockPos(en)).down()); // down might be problem

                if (!iblockstate.canEntitySpawn(en.world, en.getPosition(), en.getType())) {
                    return false;
                }

                if (en.world.getServer()
                        .getDifficulty()
                        .equals(Difficulty.PEACEFUL) == false) { // stops nasty bug of mobs trying to be spawned in peaceful and just flashing (being spawned and despawned forever)

                    if (hasLessThanMaximumEntitiesOfType((ServerWorld) en.world, en.getClassification(true))) {

                        return true;
                    }
                }
            }
        }

        return false;

    }

    static Predicate<Entity> filter = (entity) -> {
        return entity.world.getChunkProvider().isChunkLoaded(entity);
    };

    public static boolean hasLessThanMaximumEntitiesOfType(ServerWorld world,
                                                           EntityClassification enclass) {
        int entities = world.getEntities(null, filter).size();
        return entities < enclass.getMaxNumberOfCreature();

    }

    public static boolean isNearSurface(BlockPos pos, World world, int buffer) {

        BlockPos surface = WorldUtils.getSurface(world, pos);

        if (pos.getY() > surface.getY() - buffer) {
            return true;
        }

        return false;
    }
}
