package com.robertx22.world_gen.biome_color_schemes;

import com.robertx22.world_gen.biome_color_schemes.bases.BiomeColorScheme;
import com.robertx22.world_gen.biome_color_schemes.bases.BlockReplacement;
import net.minecraft.block.Blocks;

public class NetherScheme extends BiomeColorScheme {

    @Override
    public BlockReplacement OAK_LOG() {
        return new BlockReplacement(Blocks.OAK_LOG, Blocks.NETHER_BRICKS);
    }

    @Override
    public BlockReplacement OAK_PLANKS() {
        return new BlockReplacement(Blocks.OAK_PLANKS, Blocks.NETHERRACK);
    }

    @Override
    public BlockReplacement OAK_STAIRS() {
        return new BlockReplacement(Blocks.OAK_STAIRS, Blocks.NETHER_BRICK_STAIRS);
    }

    @Override
    public BlockReplacement OAK_SLABS() {
        return new BlockReplacement(Blocks.OAK_SLAB, Blocks.NETHER_BRICK_SLAB);
    }

    @Override
    public BlockReplacement OAK_FENCE() {
        return new BlockReplacement(Blocks.OAK_FENCE, Blocks.NETHER_BRICK_FENCE);
    }

}
