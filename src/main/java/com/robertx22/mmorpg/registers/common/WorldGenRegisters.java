package com.robertx22.mmorpg.registers.common;

import com.robertx22.world_gen.configs.MyChanceConfig;
import com.robertx22.world_gen.features.RandomSurfaceEggFeature;
import com.robertx22.world_gen.placements.AtSurfaceChancePlacement;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class WorldGenRegisters {
    //public static final Structure<MyIglooConfig> IGLOO = new IglooStructure();

    public static final CompositeFeature randomSurfaceChest = Biome.createCompositeFeature(new RandomSurfaceEggFeature(), IFeatureConfig.NO_FEATURE_CONFIG, new AtSurfaceChancePlacement(), new MyChanceConfig(0.5F));
    //  public static final CompositeFeature iglootest = Biome.createCompositeFeature(IGLOO, new MyIglooConfig(), Biome.PASSTHROUGH, IPlacementConfig.NO_PLACEMENT_CONFIG);

    public static void register() {

        for (Biome biome : Biome.BIOMES) {

            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, randomSurfaceChest);

            // biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, iglootest);
            //biome.addStructure(IGLOO, new MyIglooConfig());

        }

    }
}
