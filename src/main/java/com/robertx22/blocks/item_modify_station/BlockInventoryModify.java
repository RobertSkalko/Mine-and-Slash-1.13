package com.robertx22.blocks.item_modify_station;

import com.robertx22.blocks.bases.BaseInventoryBlock;
import com.robertx22.blocks.gear_factory_station.TileGearFactory;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockInventoryModify extends BaseInventoryBlock {

    public BlockInventoryModify() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {

        return new TileInventoryModify();

    }

    @Override
    public boolean onBlockActivated(IBlockState state, World world, BlockPos pos,
                                    EntityPlayer player, EnumHand hand, EnumFacing side,
                                    float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return true;

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileGearFactory) {
            player.displayGui((TileGearFactory) tile);
        }

        return true;
    }

}