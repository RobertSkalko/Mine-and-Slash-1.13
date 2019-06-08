package com.robertx22.world_gen.structures;

import com.robertx22.database.world_providers.IWP;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class SmallRandomSurfaceDecoration extends Feature<NoFeatureConfig> {

    @Override
    public boolean place(IWorld iworld,
                         ChunkGenerator<? extends GenerationSettings> generator,
                         Random rand, BlockPos pos, NoFeatureConfig config) {

        if (iworld.isAirBlock(pos)) {
            World theworld = iworld.getWorld();

            IWP iwp = WorldUtils.getIWP(theworld);

            if (iwp != null) {

                ResourceLocation res = iwp.randomSmallSurfaceDecoration();

                if (res != null) {

                    TemplateManager templatemanager = iworld.getSaveHandler()
                            .getStructureTemplateManager();

                    templatemanager.getTemplate(res)
                            .addBlocksToWorld(iworld, pos, new PlacementSettings());

                    return true;
                }
            }

        }
        return false;

    }

    String name = "random_decoration";

    public SmallRandomSurfaceDecoration() {

    }

    public int getSize() {
        return 1;
    }

}