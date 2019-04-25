package com.robertx22.advanced_blocks.repair_station;

import com.robertx22.advanced_blocks.BaseInventoryBlock;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockInventoryRepair extends BaseInventoryBlock {

    public BlockInventoryRepair() {
	super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
	return new TileInventoryRepair();
    }

}