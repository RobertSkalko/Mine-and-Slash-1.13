package com.robertx22.world_gen.features;

import com.mojang.datafixers.Dynamic;
import com.robertx22.database.world_providers.IWP;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import com.robertx22.world_gen.processors.BiomeProcessor;
import com.robertx22.world_gen.processors.ChestProcessor;
import com.robertx22.world_gen.types.FeatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public class RandomSurfaceTreasure extends Feature<NoFeatureConfig> {

    public RandomSurfaceTreasure(
            Function<Dynamic<?>, ? extends NoFeatureConfig> dynamic) {
        super(dynamic);
    }

    @Override
    public boolean place(IWorld iworld,
                         ChunkGenerator<? extends GenerationSettings> generator,
                         Random rand, BlockPos pos, NoFeatureConfig config) {

        World theworld = iworld.getWorld();

        IWP iwp = WorldUtils.getIWP(theworld);

        if (iwp != null) {

            FeatureType type = iwp.randomSmallTreasure();
            ResourceLocation res = type.structureResourceLocation;
            pos = type.modifyPos(pos);

            if (res != null) {

                TemplateManager templatemanager = ((ServerWorld) iworld.getWorld()).getSaveHandler()
                        .getStructureTemplateManager();

                PlacementSettings placement = new PlacementSettings();
                placement.addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
                placement.addProcessor(new BiomeProcessor(iwp));
                placement.addProcessor(new ChestProcessor());

                Template template = templatemanager.getTemplate(res);

                if (type.canSpawn(theworld, pos, template)) {
                    template.addBlocksToWorld(iworld, pos, placement);
                }
                return true;
            }
        }

        return false;

    }
}

