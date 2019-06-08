package com.robertx22.blocks.bases;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public abstract class NonFullBlock extends Block {

    public NonFullBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSolid(BlockState state) {
        return false;
    }

}
