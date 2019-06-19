package com.robertx22.world_gen.biome_color_schemes.bases;

import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.block.Block;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockReplacement {

    private List<BlockWeight> to;

    public BlockReplacement(Block to) {

        this.to.add(new BlockWeight(to));
    }

    public BlockReplacement(BlockWeight to) {

        this.to.add(to);
    }

    public BlockReplacement(List<BlockWeight> to) {

        this.to.addAll(to);
    }

    @Nonnull
    public Block getBlockToReplaceWith(Block block) {
        if (to == null || to.isEmpty()) {
            return block;
        } else if (to.size() == 1) {
            return to.get(0).block;
        } else if (to.size() > 1) {
            return RandomUtils.weightedRandom(to).block;
        }
        return block;

    }

}