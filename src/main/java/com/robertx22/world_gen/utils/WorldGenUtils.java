package com.robertx22.world_gen.utils;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;

import java.util.List;

public class WorldGenUtils {

    public static void generateChestWithLoot(IWorld world, BlockPos pos,
                                             List<ItemStack> items) {

        world.setBlockState(pos, Blocks.CHEST.getDefaultState(), 2); // setblockstate needs to use IWORLD, NOT WORLD

        ChestTileEntity chest = (ChestTileEntity) world.getTileEntity(pos);
        if (chest != null) {
            for (int i = 0; i < chest.getSizeInventory(); i++) {
                if (items.size() > i) {
                    chest.setInventorySlotContents(i, items.get(i));
                }
            }
        } else {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
        }
    }

    public static BlockPos getFirstSurfaceBlock(IWorld world, BlockPos pos) {
        return world.getHeight(Heightmap.Type.MOTION_BLOCKING, pos);

    }

}
