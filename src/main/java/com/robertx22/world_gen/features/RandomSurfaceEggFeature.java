package com.robertx22.world_gen.features;

import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.uncommon.capability.WorldUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class RandomSurfaceEggFeature extends Feature<NoFeatureConfig> {

    @Override
    public boolean place(IWorld iworld,
                         IChunkGenerator<? extends IChunkGenSettings> generator,
                         Random rand, BlockPos pos, NoFeatureConfig config) {

        if (iworld.isAirBlock(pos)) {
            World theworld = iworld.getWorld();

            if (WorldUtils.isMapWorld(theworld)) {

                iworld.setBlockState(pos, BlockRegister.EGG_LOOT_CRATE_BLOCK.getDefaultState(), 2); // setblockstate needs to use IWORLD, NOT WORLD

                return true;
            }

        }
        return false;

    }

}