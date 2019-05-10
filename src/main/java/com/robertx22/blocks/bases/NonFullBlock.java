package com.robertx22.blocks.bases;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public abstract class NonFullBlock extends Block {

    public NonFullBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSolid(IBlockState state) {
        return false;
    }

}
