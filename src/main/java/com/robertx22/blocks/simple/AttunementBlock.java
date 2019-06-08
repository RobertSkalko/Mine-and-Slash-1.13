package com.robertx22.blocks.simple;

import com.robertx22.blocks.bases.NonFullBlock;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class AttunementBlock extends NonFullBlock {

    public AttunementBlock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3F));
    }

    @Override
    public IItemProvider getItemDropped(BlockState state, World worldIn, BlockPos pos,
                                        int fortune) {

        return BlockRegister.ATTUNEMENT_ALTAR;
    }

    @Override
    public int getItemsToDropCount(BlockState state, int fortune, World worldIn,
                                   BlockPos pos, Random random) {
        return 1;
    }

}
