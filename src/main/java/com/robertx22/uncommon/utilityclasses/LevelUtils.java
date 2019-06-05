package com.robertx22.uncommon.utilityclasses;

import com.robertx22.config.dimension_configs.DimensionConfig;
import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class LevelUtils {

    public static int determineLevel(World world, BlockPos pos) {

        DimensionConfig config = DimensionsContainer.INSTANCE.getConfig(world);

        int lvl = 1;

        if (config.SINGLEPLAYER_MOB_SCALING) {
            EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), (double) 3000, EntitySelectors.NOT_SPECTATING);

            if (player != null) {
                lvl = Load.Unit(player).getLevel();
            }

        } else {
            lvl = determineLevelPerDistanceFromSpawn(world, pos, config) + config.MINIMUM_MOB_LEVEL - 1;
        }

        lvl = MathHelper.clamp(lvl, config.MINIMUM_MOB_LEVEL, config.MAXIMUM_MOB_LEVEL);

        return lvl;
    }

    public static int determineLevelPerDistanceFromSpawn(World world, BlockPos pos,
                                                         DimensionConfig config) {

        double distance = world.getSpawnPoint().getDistance(pos);

        int lvl = 1;

        if (distance < config.MOB_LEVEL_ONE_AREA) {
            lvl = config.MINIMUM_MOB_LEVEL;
        } else {
            lvl = (int) (1 + (distance / config.MOB_LEVEL_PER_DISTANCE));
        }

        return MathHelper.clamp(lvl, config.MINIMUM_MOB_LEVEL, config.MAXIMUM_MOB_LEVEL);

    }

    public static BlockPos getAreaPosOfLevel(World world, int level,
                                             DimensionConfig config) {

        if (level == 1) {
            return world.getSpawnPoint();
        }

        int distance = config.MOB_LEVEL_PER_DISTANCE * level;

        BlockPos pos = new BlockPos(distance, 0, world.getSpawnPoint().getZ());

        return pos;

    }

    public static int determineLevelPerDistanceFromSpawn(World world, BlockPos pos) {

        return determineLevelPerDistanceFromSpawn(world, pos, DimensionsContainer.INSTANCE
                .getConfig(world));

    }

}
