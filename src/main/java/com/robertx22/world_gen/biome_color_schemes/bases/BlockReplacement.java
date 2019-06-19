package com.robertx22.world_gen.biome_color_schemes.bases;

import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.block.Block;

import java.util.List;

public class BlockReplacement {

    public Block from;
    private List<BlockWeight> to;

    public BlockReplacement(Block from, Block to) {
        this.from = from;
        this.to.add(new BlockWeight(to));
    }

    public BlockReplacement(Block from, BlockWeight to) {
        this.from = from;
        this.to.add(to);
    }

    public BlockReplacement(Block from, List<BlockWeight> to) {
        this.from = from;
        this.to.addAll(to);
    }

    public Block getBlockToReplaceWith() {
        return RandomUtils.weightedRandom(to).block;
    }

}