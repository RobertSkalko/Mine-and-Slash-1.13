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

        ITEMS = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("ITEM", builder))
                .getLeft();
        RUNED_ITEMS = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("RUNED_ITEMS", builder))
                .getLeft();
        RUNES = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("RUNES", builder))
                .getLeft();
        MOBS = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("MOBS", builder))
                .getLeft();
        MAPS = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("MAPS", builder))
                .getLeft();

        CURRENCY = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("CURRENCY", builder))
                .getLeft();

        SPELLS = builder.configure((ForgeConfigSpec.Builder prefix) -> new RarityWeight("SPELLS", builder))
                .getLeft();

        builder.pop();
    }

}
