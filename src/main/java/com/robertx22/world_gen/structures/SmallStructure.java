package com.robertx22.world_gen.structures;

import com.robertx22.database.world_providers.IWP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class SmallStructure extends Feature<NoFeatureConfig> {

    @Override
    public boolean place(IWorld iworld,
                         IChunkGenerator<? extends IChunkGenSettings> generator,
                         Random rand, BlockPos pos, NoFeatureConfig config) {

        if (iworld.isAirBlock(pos)) {
            World theworld = iworld.getWorld();

            if (theworld instanceof IWP) {

                IWP iwp = (IWP) theworld;

                TemplateManager templatemanager = iworld.getSaveHandler()
                        .getStructureTemplateManager();

                templatemanager.getTemplate(iwp.randomSmallSurfaceDecoration())
                        .addBlocksToWorld(iworld, pos, new PlacementSettings());

                return true;
            }

        }
        return false;

    }

    String name = "random_decoration";

    public SmallStructure() {

    }

    public int getSize() {
        return 1;
    }

    protected int getSeedModifier() {
        return 14357618;
    }

}