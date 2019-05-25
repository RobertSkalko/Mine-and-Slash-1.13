package com.robertx22.uncommon.capability;

import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.LevelUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtils {

    public static BlockPos getPosByLevel(World world, int lvl) {

        BlockPos pos = world.getSpawnPoint();

        int destinationLvl = LevelUtils.determineLevel(world, pos);

        while (destinationLvl != lvl) {

            if (destinationLvl < lvl) {
                pos = pos.south(10);
            } else {
                pos = pos.north();
            }
            destinationLvl = LevelUtils.determineLevel(world, pos);
        }

        pos = getSurface(world, pos);

        return pos;

    }

    public static BlockPos getSurface(World world, BlockPos pos) {

        pos = pos.add(0, world.getHeight(), 0);

        while (world.getBlockState(pos).equals(Blocks.AIR.getDefaultState())) {

            pos = pos.down();

        }

        return pos.up();

    }

    public static boolean isMapWorld(World world) {

        if (world.getDimension() instanceof BaseWorldProvider == false) {
            return DimensionsContainer.INSTANCE.getConfig(world).MAP_TIER > 0;
        }

        return true;

    }

    public int getTier(World world, EntityPlayer player) {

        if (DimensionsContainer.INSTANCE.hasConfig(world)) {
            return DimensionsContainer.INSTANCE.getConfig(world).MAP_TIER;
        } else {

            return Load.playerMapData(player).getTier();
        }

    }

    public static boolean dropsUniques(World world) {

        if (isMapWorld(world) == true) {
            return true;
        }

        return DimensionsContainer.INSTANCE.getConfig(world).DROPS_UNIQUE_ITEMS;
    }

}
