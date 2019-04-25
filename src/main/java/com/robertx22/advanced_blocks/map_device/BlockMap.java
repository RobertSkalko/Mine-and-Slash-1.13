package com.robertx22.advanced_blocks.map_device;

import com.robertx22.advanced_blocks.BaseInventoryBlock;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockMap extends BaseInventoryBlock {

    public BlockMap() {
	super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
	return new TileMap();
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand,
	    EnumFacing side, float hitX, float hitY, float hitZ) {
	if (world.isRemote)
	    return true;

	TileEntity tile = world.getTileEntity(pos);

	if (tile instanceof TileMap) {
	    player.displayGui((TileMap) tile);
	}

	return true;
    }

}