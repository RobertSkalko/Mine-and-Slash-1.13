package com.robertx22.mmorpg.registers.common;

import com.robertx22.world_gen.configs.MyChanceConfig;
import com.robertx22.world_gen.features.RandomSurfaceEggFeature;
import com.robertx22.world_gen.placements.AtSurfaceChancePlacement;
import com.robertx22.world_gen.structures.BigWoodPillar;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.BasePlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class WorldGenRegisters {

    static final float SMALL_DECO_CHANCE = 2F;
    static final CompositeFeature randomSurfaceChest = create(new RandomSurfaceEggFeature(), new AtSurfaceChancePlacement(), new MyChanceConfig(1F));
    static final CompositeFeature bigWoodPillar = createSmallSurfaceDeco(new BigWoodPillar());

    public static void register() {

        for (Biome biome : Biome.BIOMES) {

            add(biome, randomSurfaceChest);
            add(biome, bigWoodPillar);

            // biome.addStructure(new BigWoodPillar(), IFeatureConfig.NO_FEATURE_CONFIG);

        }

    }

    static void add(Biome biome, CompositeFeature comp) {
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, comp);
    }

    static CompositeFeature createSmallSurfaceDeco(Feature feature) {

        return Biome.createCompositeFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, new AtSurfaceChancePlacement(), new MyChanceConfig(SMALL_DECO_CHANCE));
    }

    static <F extends IFeatureConfig, D extends IPlacementConfig> CompositeFeature<F, D> create(
            Feature feature, BasePlacement<D> basePlacementIn, D placementConfig) {

        return Biome.createCompositeFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, basePlacementIn, placementConfig);
    }

}
