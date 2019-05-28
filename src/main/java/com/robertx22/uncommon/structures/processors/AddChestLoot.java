package com.robertx22.uncommon.structures.processors;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.feature.template.ITemplateProcessor;
import net.minecraft.world.gen.feature.template.Template.BlockInfo;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.ArrayList;
import java.util.List;

public class AddChestLoot implements ITemplateProcessor {

    @Override
    public BlockInfo processBlock(IBlockReader world, BlockPos pos,
                                  BlockInfo blockInfoIn) {

        if (true) {
            return blockInfoIn;
        }

        if (blockInfoIn.blockState.getBlock()
                .equals(Blocks.CHEST.getDefaultState().getBlock())) {

            if (world instanceof ICapabilityProvider == false) {
                return blockInfoIn;
            }

            //int lvl = LevelUtils.determineLevelPerDistanceFromSpawn(world, pos);

            List<ItemStack> items = new ArrayList<>();

            /*
            while (items.size() < 2) {
                for (ItemStack stack : MasterLootGen.generateLoot((World) world, 1F, lvl)) {
                    items.add(stack);
                }
            }

             */

            TileEntityChest chest = new TileEntityChest();

            for (int i = 0; i < chest.getSizeInventory(); i++) {
                if (items.size() > i) {
                    chest.setInventorySlotContents(i, items.get(i));
                }
            }

            return new BlockInfo(blockInfoIn.pos, Blocks.CHEST.getDefaultState(), chest.serializeNBT());

        }

        return blockInfoIn;
    }

}
