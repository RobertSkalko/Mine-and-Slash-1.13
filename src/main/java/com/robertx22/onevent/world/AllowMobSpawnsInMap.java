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

        LivingEntity en = event.getEntityLiving();

        if (en.world.isRemote) {
            return;
        }
        if (en instanceof PlayerEntity) {
            return;
        }

        if (RandomUtils.roll(80)) {

            if (EntityTypeUtils.isMob(en)) {

                if (WorldUtils.isMapWorld(event.getWorld().getWorld())) {

                    //Watch watch = new Watch().min(100);

                    if (en instanceof SlimeEntity) {
                        return;
                        // no
                    } else {

                        if (isNearSurface(en.getPosition(), en.world, 5)) {

                            BlockState iblockstate = en.world.getBlockState((new BlockPos(en))
                                    .down()); // down might be problem

                            if (!iblockstate.canEntitySpawn(en.world, en.getPosition(), en
                                    .getType())) {
                                return;
                            }

                            if (en.world.getServer()
                                    .getDifficulty()
                                    .equals(Difficulty.PEACEFUL) == false) {

                                if (hasLessThanMaximumEntitiesOfType((ServerWorld) en.world, en)) {

                                    event.setResult(Result.DENY);
                                    en.world.addEntity(en); // i spawn it myself cus otherwise minecraft checks again things and sometimes doesnt spawn it..
                                }
                            } else {
                                System.out.println("stop");
                            }
                        }
                    }

                    // watch.print("spawn ");
                }
            }
        }

    }

    static Predicate<Entity> filter = (entity) -> {
        return entity.world.getChunkProvider().isChunkLoaded(entity);
    };

    public static boolean hasLessThanMaximumEntitiesOfType(ServerWorld world, Entity en) {

        EntityClassification entityclassification = en.getClassification(true);
        int entities = world.getEntities(en.getType(), filter).size();
        return entities < entityclassification.getMaxNumberOfCreature();

    }

    public static boolean isNearSurface(BlockPos pos, World world, int buffer) {

        BlockPos surface = WorldUtils.getSurface(world, pos);

        if (pos.getY() > surface.getY() - buffer) {
            return true;
        }

        return false;
    }
}
