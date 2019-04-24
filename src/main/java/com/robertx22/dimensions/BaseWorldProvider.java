package com.robertx22.dimensions;

import net.minecraft.world.dimension.OverworldDimension;

public abstract class BaseWorldProvider extends OverworldDimension implements IWP {

    public BaseWorldProvider(boolean nothing) {

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