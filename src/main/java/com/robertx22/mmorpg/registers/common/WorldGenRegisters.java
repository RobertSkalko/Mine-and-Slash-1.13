package com.robertx22.mmorpg.registers.common;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.world_gen.features.RandomSurfaceDecoration;
import com.robertx22.world_gen.features.RandomSurfaceEggFeature;
import com.robertx22.world_gen.features.RandomSurfaceTreasure;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithChance;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class WorldGenRegisters {

    public static final int SMALL_DECO_CHANCE = 25;
    public static final ConfiguredFeature randomSurfaceChest = create(new RandomSurfaceEggFeature(NoFeatureConfig::deserialize), new AtSurfaceWithChance(ChanceConfig::deserialize), new ChanceConfig(1000));
    public static final ConfiguredFeature smallRandomSurfaceDecoration = createSmallSurfaceDeco(new RandomSurfaceDecoration(NoFeatureConfig::deserialize));
    public static final ConfiguredFeature smallRandomSurfaceTreasure = Biome.createDecoratedFeature(new RandomSurfaceTreasure(NoFeatureConfig::deserialize), IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(100));

    public static void register() {

        System.out.println("Registering Mine and Slash Map World Gen");

        for (Biome biome : ForgeRegistries.BIOMES) { // this works!

            // only register world gen where it can actually be used
            if (WorldProviders.All.values()
                    .stream()
                    .filter(iwp -> iwp.getBiome().equals(biome))
                    .collect(Collectors.toList())
                    .size() > 0) {

                add(biome, randomSurfaceChest);
                add(biome, smallRandomSurfaceDecoration);
                add(biome, smallRandomSurfaceTreasure);

            }
        }

        // biome.addStructure(new BigWoodPillar(), IFeatureConfig.NO_FEATURE_CONFIG);

    }

    public static void add(Biome biome, ConfiguredFeature comp) {
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, comp);
    }

    public static ConfiguredFeature createSmallSurfaceDeco(Feature feature) {

        return Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(SMALL_DECO_CHANCE));
    }

    public static <F extends IFeatureConfig, D extends IPlacementConfig> ConfiguredFeature<F> create(
            Feature feature, Placement<D> basePlacementIn, D placementConfig) {

        return Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, basePlacementIn, placementConfig);
    }

}
