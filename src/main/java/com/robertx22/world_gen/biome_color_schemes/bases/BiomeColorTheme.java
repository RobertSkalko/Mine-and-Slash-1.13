package com.robertx22.world_gen.biome_color_schemes.bases;

import com.robertx22.world_gen.biome_color_schemes.NetherTheme;
import com.robertx22.world_gen.biome_color_schemes.NormalTheme;
import com.robertx22.world_gen.biome_color_schemes.WinterSpruceTheme;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.List;

public abstract class BiomeColorTheme {

    public BiomeColorTheme() {

        blocksReplaceMap.put(Blocks.OAK_LOG, new BlockReplacement(OAK_LOG()));
        blocksReplaceMap.put(Blocks.OAK_PLANKS, new BlockReplacement(OAK_PLANKS()));
        blocksReplaceMap.put(Blocks.OAK_STAIRS, new BlockReplacement(OAK_STAIRS()));
        blocksReplaceMap.put(Blocks.OAK_SLAB, new BlockReplacement(OAK_SLABS()));
        blocksReplaceMap.put(Blocks.OAK_FENCE, new BlockReplacement(OAK_FENCE()));

    }

    public abstract List<BlockWeight> OAK_LOG();

    public abstract List<BlockWeight> OAK_PLANKS();

    public abstract List<BlockWeight> OAK_STAIRS();

    public abstract List<BlockWeight> OAK_SLABS();

    public abstract List<BlockWeight> OAK_FENCE();

    public HashMap<Block, BlockReplacement> blocksReplaceMap = new HashMap<>();

    public static final NetherTheme NETHER = new NetherTheme();
    public static final NormalTheme NORMAL = new NormalTheme();
    public static final WinterSpruceTheme WINTER_SPRUCE = new WinterSpruceTheme();

}
