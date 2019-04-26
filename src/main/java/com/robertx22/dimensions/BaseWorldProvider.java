package com.robertx22.dimensions;

import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.DebugGenSettings;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;

public abstract class BaseWorldProvider extends OverworldDimension implements IWP {
    DimensionType type;

    public BaseWorldProvider(DimensionType type) {
	this.type = type;
    }

    BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeprovidertype = BiomeProviderType.FIXED;

    @Override
    public IChunkGenerator<? extends IChunkGenSettings> createChunkGenerator() {

	SingleBiomeProviderSettings setting = biomeprovidertype.createSettings().setBiome(getBiome());

	return ChunkGeneratorType.DEBUG.create(this.world, biomeprovidertype.create(setting), new DebugGenSettings());

    }

    @Override
    public int Weight() {
	return UncommonWeight;

    }

    @Override
    public boolean canDropChunk(int x, int z) {
	return true;
    }

    /**
     * 
     * Do not override this.
     * 
     * Returns true on clients (to allow rendering of sky etc, maybe even clouds).
     * Returns false on servers (to disable Nether Portal mob spawning and sleeping
     * in beds).
     */
    @Override
    public boolean isSurfaceWorld() {
	return (this.world == null) ? false : this.world.isRemote;
    }

    @Override
    public boolean canRespawnHere() {
	return false;
    }

}