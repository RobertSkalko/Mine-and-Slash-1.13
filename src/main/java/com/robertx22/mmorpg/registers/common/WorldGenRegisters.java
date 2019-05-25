package com.robertx22.mmorpg.registers.common;

import net.minecraft.world.biome.Biome;

public class WorldGenRegisters {
    //public static final Structure<MyIglooConfig> IGLOO = new IglooStructure();

    //  public static final CompositeFeature randomSurfaceChest = Biome.createCompositeFeature(new RandomSurfaceChestFeature(), IFeatureConfig.NO_FEATURE_CONFIG, new RandomSurfaceChancePlacement(), new ChanceConfig(1));
    //  public static final CompositeFeature iglootest = Biome.createCompositeFeature(IGLOO, new MyIglooConfig(), Biome.PASSTHROUGH, IPlacementConfig.NO_PLACEMENT_CONFIG);

    public static void register() {

        for (Biome biome : Biome.BIOMES) {
            //biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, randomSurfaceChest);

            // biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, iglootest);
            //biome.addStructure(IGLOO, new MyIglooConfig());

        }

    }
}
