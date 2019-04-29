package com.robertx22.config;

import com.robertx22.config.base.RarityWeight;
import net.minecraftforge.common.ForgeConfigSpec;

public class RarityDropratesConfig {

    public static final String NAME = "RARITY_WEIGHTS";

    public RarityWeight ITEMS;
    public RarityWeight RUNED_ITEMS;
    public RarityWeight RUNES;
    public RarityWeight MOBS;
    public RarityWeight MAPS;
    public RarityWeight CURRENCY;
    public RarityWeight SPELLS;

    RarityDropratesConfig(ForgeConfigSpec.Builder builder) {
        builder.push("rarity_weights");

        ITEMS = builder.configure(RarityWeight::new).getLeft();
        RUNED_ITEMS = builder.configure(RarityWeight::new).getLeft();
        RUNES = builder.configure(RarityWeight::new).getLeft();
        MOBS = builder.configure(RarityWeight::new).getLeft();
        MAPS = builder.configure(RarityWeight::new).getLeft();
        CURRENCY = builder.configure(RarityWeight::new).getLeft();
        SPELLS = builder.configure(RarityWeight::new).getLeft();

        builder.pop();
    }

}
