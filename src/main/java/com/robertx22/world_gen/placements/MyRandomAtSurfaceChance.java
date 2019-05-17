package com.robertx22.world_gen.placements;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.BasePlacement;
import net.minecraft.world.gen.placement.ChanceConfig;

import java.util.Random;

public class MyRandomAtSurfaceChance extends BasePlacement<ChanceConfig> {
    public <C extends IFeatureConfig> boolean generate(IWorld worldIn,
                                                       IChunkGenerator<? extends IChunkGenSettings> chunkGenerator,
                                                       Random random, BlockPos pos,
                                                       ChanceConfig placementConfig,
                                                       Feature<C> featureIn,
                                                       C featureConfig) {

        if (placementConfig.chance > random.nextFloat() * 100) {

            //  IWorldData iworlddata = Load.World(worldIn.getWorld()); // TODO TEST IF THIS WORKS ON MAPWORLDS, maybe the world instance is not full enough?

            // if (iworlddata.isMapWorld()) { TODO
            int x = 0;//random.nextInt(16);
            int z = 0;//random.nextInt(16);
            BlockPos blockpos = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING, pos.add(x, 0, z));
            featureIn.place(worldIn, chunkGenerator, random, blockpos, featureConfig);
            // }
            return true;
        }

        return false;

    }
}