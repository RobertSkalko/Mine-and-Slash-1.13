package com.robertx22.blocks.map_device;

import com.robertx22.blocks.bases.BaseInventoryBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockMapDevice extends BaseInventoryBlock {

    public BlockMapDevice() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return new TileMapDevice();

    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos,
                                    PlayerEntity player, Hand hand,
                                    BlockRayTraceResult ray) {
        if (world.isRemote) {
            return true;
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileMapDevice) {

            TileMapDevice map = (TileMapDevice) tile;

            player.openContainer((INamedContainerProvider) map.createMenu(1, player.inventory, player));

            //  player.openContainer(new ContainerMapDevice(5, player.inventory, (TileMapDevice) tile));

        }

        return true;
    }

}