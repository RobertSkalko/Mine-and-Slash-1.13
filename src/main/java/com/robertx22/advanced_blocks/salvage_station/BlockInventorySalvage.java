package com.robertx22.advanced_blocks.salvage_station;

import com.robertx22.advanced_blocks.BaseInventoryBlock;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockInventorySalvage extends BaseInventoryBlock {

    public BlockInventorySalvage() {
	super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
	return new TileInventorySalvage();
    }

}