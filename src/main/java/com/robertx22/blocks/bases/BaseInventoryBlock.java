package com.robertx22.blocks.bases;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class BaseInventoryBlock extends NonFullBlock {
    protected BaseInventoryBlock(Properties prop) {
        super(prop);

    }

    @Override
    public IItemProvider getItemDropped(BlockState state, World worldIn, BlockPos pos,
                                        int fortune) {

        return this;
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IInventory) {
            InventoryHelper.dropInventoryItems((World) worldIn, pos, (IInventory) tileEntity);
        }
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IInventory) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
