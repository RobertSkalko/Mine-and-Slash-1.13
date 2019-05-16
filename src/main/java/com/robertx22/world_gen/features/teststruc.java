package com.robertx22.world_gen.features;

import com.robertx22.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class teststruc extends Structure<NoFeatureConfig> {
    @Override
    public boolean place(IWorld worldIn,
                         IChunkGenerator<? extends IChunkGenSettings> generator,
                         Random rand, BlockPos pos, NoFeatureConfig config) {

        TemplateManager manager = worldIn.getSaveHandler().getStructureTemplateManager();

        Template template = manager.getTemplate(new ResourceLocation(Ref.MODID, "dungeon_entrance_0_part0")); // dont add .nbt to name

        PlacementSettings placementsettings = new PlacementSettings();

        if (template != null) {
            template.addBlocksToWorld(worldIn, pos, placementsettings); // cant be more than 1 chunk it seems...
            return true;
        }

        return false;
    }

    @Override
    protected boolean hasStartAt(IChunkGenerator<?> chunkGen, Random rand, int chunkPosX,
                                 int chunkPosZ) {
        return false;
    }

    @Override
    protected boolean isEnabledIn(IWorld worldIn) {
        return false;
    }

    @Override
    protected StructureStart makeStart(IWorld worldIn, IChunkGenerator<?> generator,
                                       SharedSeedRandom random, int x, int z) {
        return null;
    }

    @Override
    protected String getStructureName() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
