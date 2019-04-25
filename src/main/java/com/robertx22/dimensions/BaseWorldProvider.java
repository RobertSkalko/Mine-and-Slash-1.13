package com.robertx22.dimensions;

import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;

public abstract class BaseWorldProvider extends OverworldDimension implements IWP {

    public BaseWorldProvider(boolean nothing) {

    }

    BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeprovidertype = BiomeProviderType.FIXED;

    @Override
    public IChunkGenerator<? extends IChunkGenSettings> createChunkGenerator() {

	SingleBiomeProviderSettings singlebiomeprovidersettings2 = biomeprovidertype.createSettings()
		.setBiome(this.getbi);
	biomeprovider = biomeprovidertype.create(singlebiomeprovidersettings2);

    }

    @Override
    public int Weight() {
	return this.UncommonWeight;

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