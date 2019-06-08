package com.robertx22.mmorpg.registers.common;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.world_gen.configs.MyChanceConfig;
import com.robertx22.world_gen.features.RandomSurfaceEggFeature;
import com.robertx22.world_gen.placements.AtSurfaceChancePlacement;
import com.robertx22.world_gen.structures.SmallRandomSurfaceDecoration;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class WorldGenRegisters {

    public static final float SMALL_DECO_CHANCE = 1F;
    public static final ConfiguredFeature randomSurfaceChest = create(new RandomSurfaceEggFeature(), new AtSurfaceChancePlacement(), new MyChanceConfig(1F));
    public static final ConfiguredFeature smallRandomSurfaceDecoration = createSmallSurfaceDeco(new SmallRandomSurfaceDecoration());

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
            }
        }

        // biome.addStructure(new BigWoodPillar(), IFeatureConfig.NO_FEATURE_CONFIG);

    }

    public static void add(Biome biome, ConfiguredFeature comp) {
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, comp);
    }

    public static ConfiguredFeature createSmallSurfaceDeco(Feature feature) {

        return Biome.func_222280_a(feature, IFeatureConfig.NO_FEATURE_CONFIG, new AtSurfaceChancePlacement(), new MyChanceConfig(SMALL_DECO_CHANCE));
    }

    public static <F extends IFeatureConfig, D extends IPlacementConfig> ConfiguredFeature<F> create(
            Feature feature, Placement<D> basePlacementIn, D placementConfig) {

        return Biome.func_222280_a(feature, IFeatureConfig.NO_FEATURE_CONFIG, basePlacementIn, placementConfig);
    }

}
