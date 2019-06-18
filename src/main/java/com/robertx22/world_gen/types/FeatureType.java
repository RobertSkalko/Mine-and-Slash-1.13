package com.robertx22.world_gen.types;

import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class FeatureType implements IWeighted {

    public FeatureType(ResourceLocation loc) {
        this.structureResourceLocation = loc;
    }

    public ResourceLocation structureResourceLocation;

    int weight = 1000;
    int lowerByXBlocks = 0;

    public FeatureType lowerBy(int blocks) {
        this.lowerByXBlocks = blocks;
        return this;
    }

    public BlockPos modifyPos(BlockPos pos) {

        pos = pos.down(lowerByXBlocks);

        return pos;
    }

    @Override
    public int Weight() {
        return this.weight;
    }
}
