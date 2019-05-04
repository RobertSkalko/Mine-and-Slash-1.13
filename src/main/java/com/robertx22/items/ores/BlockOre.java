package com.robertx22.items.ores;

import com.robertx22.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOre extends Block {

    int minDropAmount = 1;
    int maxDropAmount = 3;

    int rarity;

    public BlockOre(int rarity, Material material) {
        super(Block.Properties.create(material).hardnessAndResistance(2F));
        this.rarity = rarity;

        this.setRegistryName(Ref.MODID + ":ore_block" + rarity);

    }

    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos,
                                        int fortune) {

        return ItemOre.ItemOres.get(rarity);
    }

    @Override
    public int getItemsToDropCount(IBlockState state, int fortune, World worldIn,
                                   BlockPos pos, Random random) {
        if (this.minDropAmount > this.maxDropAmount) {
            int i = this.minDropAmount;
            this.minDropAmount = this.maxDropAmount;
            this.maxDropAmount = i;
        }
        return this.minDropAmount + random.nextInt(this.maxDropAmount - this.minDropAmount + 1);
    }

}