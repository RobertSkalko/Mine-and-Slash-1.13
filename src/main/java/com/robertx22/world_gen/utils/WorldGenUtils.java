package com.robertx22.world_gen.utils;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.List;

public class WorldGenUtils {

    public static void generateChestWithLoot(IWorld world, BlockPos pos,
                                             List<ItemStack> items) {

        world.setBlockState(pos, Blocks.CHEST.getDefaultState(), 2); // setblockstate needs to use IWORLD, NOT WORLD

        TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos);
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
}
