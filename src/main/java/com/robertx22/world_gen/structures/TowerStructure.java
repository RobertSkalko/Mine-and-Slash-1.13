package com.robertx22.world_gen.structures;

import com.mojang.datafixers.Dynamic;
import com.robertx22.mmorpg.Ref;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class TowerStructure extends ScatteredStructure<NoFeatureConfig> {

    public TowerStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> dynamic) {
        super(dynamic);
    }

    @Override
    public String getStructureName() {
        return Ref.MODID + ":tower";
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public Structure.IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    protected int getSeedModifier() {
        return 14357618;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome,
                     MutableBoundingBox boundingbox, int referenceIn, long longNum) {
            super(structure, chunkX, chunkZ, biome, boundingbox, referenceIn, longNum);

        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn,
                         int chunkX, int chunkZ, Biome biomeIn) {

            int x = chunkX * 16;
            int z = chunkZ * 16;

            ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);

            x = TowerPieces.clamp(x, chunkPos.getXStart(), chunkPos.getXEnd());
            z = TowerPieces.clamp(z, chunkPos.getZStart(), chunkPos.getZEnd());

            BlockPos blockpos = new BlockPos(x, 90, z);

            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            TowerPieces.init(templateManagerIn, blockpos, rotation, this.components, this.rand, biomeIn, chunkPos);
            this.recalculateStructureSize();

        }
    }

}