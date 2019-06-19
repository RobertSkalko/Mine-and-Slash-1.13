package com.robertx22.world_gen.biome_color_schemes.bases;

import com.robertx22.world_gen.biome_color_schemes.NetherScheme;

public abstract class BiomeColorScheme {

    public abstract BlockReplacement OAK_LOG();

    public abstract BlockReplacement OAK_PLANKS();

    public abstract BlockReplacement OAK_STAIRS();

    public abstract BlockReplacement OAK_SLABS();

    public abstract BlockReplacement OAK_FENCE();

    public static final NetherScheme NETHER = new NetherScheme();

}
