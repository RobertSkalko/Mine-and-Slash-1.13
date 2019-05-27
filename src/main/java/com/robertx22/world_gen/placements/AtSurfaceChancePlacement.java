package com.robertx22.world_gen.placements;

import com.robertx22.uncommon.utilityclasses.WorldUtils;
import com.robertx22.world_gen.configs.MyChanceConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.BasePlacement;

import java.util.Random;

public class AtSurfaceChancePlacement extends BasePlacement<MyChanceConfig> {
    public <C extends IFeatureConfig> boolean generate(IWorld worldIn,
                                                       IChunkGenerator<? extends IChunkGenSettings> chunkGenerator,
                                                       Random random, BlockPos pos,
                                                       MyChanceConfig placementConfig,
                                                       Feature<C> featureIn,
                                                       C featureConfig) {

        if (placementConfig.chance > random.nextFloat() * 100) {

            if (WorldUtils.isMapWorld(worldIn.getWorld())) {

                int x = random.nextInt(16);
                int z = random.nextInt(16);
                BlockPos blockpos = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING, pos.add(x, 0, z));
                featureIn.place(worldIn, chunkGenerator, random, blockpos, featureConfig);

                return true;
            }
        }

        return false;

    }
}