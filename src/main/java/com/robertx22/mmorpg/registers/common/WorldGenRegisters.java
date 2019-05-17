package com.robertx22.mmorpg.registers.common;

import com.robertx22.deobsfucation.IglooPieces;
import com.robertx22.deobsfucation.IglooStructure;
import com.robertx22.deobsfucation.MyIglooConfig;
import com.robertx22.world_gen.features.RandomSurfaceChestFeature;
import com.robertx22.world_gen.placements.RandomSurfaceChancePlacement;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class WorldGenRegisters {
    public static final Structure<MyIglooConfig> IGLOO = new IglooStructure();

    public static final CompositeFeature randomSurfaceChest = Biome.createCompositeFeature(new RandomSurfaceChestFeature(), IFeatureConfig.NO_FEATURE_CONFIG, new RandomSurfaceChancePlacement(), new ChanceConfig(1));
    public static final CompositeFeature iglootest = Biome.createCompositeFeature(IGLOO, new MyIglooConfig(), Biome.PASSTHROUGH, IPlacementConfig.NO_PLACEMENT_CONFIG);

    public static void register() {

        IglooPieces.registerPieces();

        for (Biome biome : Biome.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, randomSurfaceChest);

            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, iglootest);
            biome.addStructure(IGLOO, new MyIglooConfig());

        }

    }
}
