package com.robertx22.deobsfucation;

import net.minecraft.init.Biomes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.IglooConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class IglooStructure extends ScatteredStructure<IglooConfig> {
    protected String getStructureName() {
        return "Igloo";
    }

    public int getSize() {
        return 3;
    }

    protected StructureStart makeStart(IWorld worldIn, IChunkGenerator<?> generator,
                                       SharedSeedRandom random, int x, int z) {
        Biome biome = generator.getBiomeProvider()
                .getBiome(new BlockPos((x << 4) + 9, 0, (z << 4) + 9), Biomes.PLAINS);
        return new IglooStructure.Start(worldIn, generator, random, x, z, biome);
    }

    protected int getSeedModifier() {
        return 14357618;
    }

    public static class Start extends StructureStart {
        public Start() {
        }

        public Start(IWorld iworld, IChunkGenerator<?> iChunkGenerator,
                     SharedSeedRandom sharedSeedRandom, int chunkPosX, int chunkPosZ,
                     Biome biome) {

            super(chunkPosX, chunkPosZ, biome, sharedSeedRandom, iworld.getSeed());
            IglooConfig iglooconfig = (IglooConfig) iChunkGenerator.getStructureConfig(biome, Feature.IGLOO);
            int i = chunkPosX * 16;
            int j = chunkPosZ * 16;
            BlockPos blockpos = new BlockPos(i, 90, j);
            Rotation rotation = Rotation.values()[sharedSeedRandom.nextInt(Rotation.values().length)];
            TemplateManager templatemanager = iworld.getSaveHandler()
                    .getStructureTemplateManager();
            IglooPieces.addPieces(templatemanager, blockpos, rotation, this.components, sharedSeedRandom, iglooconfig);
            this.recalculateStructureSize(iworld);
        }
    }
}