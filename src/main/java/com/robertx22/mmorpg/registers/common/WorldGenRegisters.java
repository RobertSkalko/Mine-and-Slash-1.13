package com.robertx22.mmorpg.registers.common;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.world_gen.features.RandomSurfaceDecoration;
import com.robertx22.world_gen.features.RandomSurfaceEggFeature;
import com.robertx22.world_gen.features.RandomSurfaceTreasure;
import net.minecraft.util.registry.Registry;
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

    // public static final Structure<NoFeatureConfig> towerStructure = register(Ref.MODID + ":tower_structure", new TowerStructure(NoFeatureConfig::deserialize));
    //private static final ConfiguredFeature<?> towerFeature = Biome.createDecoratedFeature(towerStructure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG);

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

                //add(biome, towerFeature);
                //biome.addStructure(towerStructure, IFeatureConfig.NO_FEATURE_CONFIG);
            }
        }

    }

    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key,
                                                                               F value) {
        return (F) (Registry.<Feature<?>>register(Registry.FEATURE, key, value));
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
