package com.robertx22.mmorpg.registers.common;

import com.robertx22.world_gen.features.RandomSurfaceChestFeature;
import com.robertx22.world_gen.placements.RandomSurfaceChancePlacement;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;

public class WorldGenRegisters {
    public static final CompositeFeature randomSurfaceChest = Biome.createCompositeFeature(new RandomSurfaceChestFeature(), IFeatureConfig.NO_FEATURE_CONFIG, new RandomSurfaceChancePlacement(), new ChanceConfig(1));

    public static void register() {

        for (Biome biome : Biome.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, randomSurfaceChest);

        }

    }
}
