package com.robertx22.items.misc;

import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.mmorpg.registers.common.BlockRegister;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;

public class ItemMap extends Item {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemMap() {
        super(new Properties());

    }

    public static void createMapPortal(DimensionType type, BlockPos pos, World world,
                                       MapItemData data) {
        IWorldData currentdata = Load.World(world);

        if (currentdata.isMapWorld()) {

        } else {

            if (data != null) {

                summonPortal(world, pos, type);
            }
        }
    }

    private static void summonPortal(World world, BlockPos pos, DimensionType type) {

        spawnPortalBlock(world, pos, type);

        spawnFrameBlock(world, pos.south());
        spawnFrameBlock(world, pos.north());
        spawnFrameBlock(world, pos.east());
        spawnFrameBlock(world, pos.west());

        spawnFrameBlock(world, pos.south().east());
        spawnFrameBlock(world, pos.south().west());
        spawnFrameBlock(world, pos.north().east());
        spawnFrameBlock(world, pos.north().west());

    }

    private static void spawnPortalBlock(World world, BlockPos pos, DimensionType type) {

        Block block = world.getBlockState(pos).getBlock();

        if (block.equals(Blocks.AIR) || block.equals(BlockRegister.PORTAL_BLOCK)) {

            world.setBlockState(pos, BlockRegister.PORTAL_BLOCK.getDefaultState(), 2);
            TileMapPortal portal = new TileMapPortal(type);
            world.setTileEntity(pos, portal);
        }
    }

    private static Block FRAME_BLOCK = Blocks.COBBLESTONE;

    private static void spawnFrameBlock(World world, BlockPos pos) {

        Block block = world.getBlockState(pos).getBlock();
        if (block.equals(Blocks.AIR) || block.equals(FRAME_BLOCK)) {

            world.setBlockState(pos, FRAME_BLOCK.getDefaultState(), 2);
        }
    }

}