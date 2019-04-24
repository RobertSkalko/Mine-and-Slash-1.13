package com.robertx22.advanced_blocks;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.gui.GuiHandler;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class BaseInventoryBlock extends BlockContainer {
    protected BaseInventoryBlock(Properties prop) {
	super(prop);

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
	    EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
	if (worldIn.isRemote)
	    return true;

	playerIn.openGui(Main.instance, GuiHandler.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
	return true;
    }

    @Override
    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
	return this;
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, IBlockState state) {
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

    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getBlockLayer() {
	return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isFullCube(IBlockState iBlockState) {
	return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
	return EnumBlockRenderType.MODEL;
    }
}
