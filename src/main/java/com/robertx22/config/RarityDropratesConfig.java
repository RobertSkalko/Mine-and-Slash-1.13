package com.robertx22.config;

import com.robertx22.config.base.RarityWeight;
import net.minecraftforge.common.ForgeConfigSpec;

public class RarityDropratesConfig {

    public static final String NAME = "RARITY_WEIGHTS";

    public ForgeConfigSpec.ConfigValue<RarityWeight> ITEMS;
    public ForgeConfigSpec.ConfigValue<RarityWeight> RUNED_ITEMS;
    public ForgeConfigSpec.ConfigValue<RarityWeight> RUNES;
    public ForgeConfigSpec.ConfigValue<RarityWeight> MOBS;
    public ForgeConfigSpec.ConfigValue<RarityWeight> MAPS;
    public ForgeConfigSpec.ConfigValue<RarityWeight> CURRENCY;
    public ForgeConfigSpec.ConfigValue<RarityWeight> SPELLS;

    RarityDropratesConfig(ForgeConfigSpec.Builder builder) {
        builder.push("rarity_weights");

        ITEMS = builder.translation("mmorpg.word.item")
                .define("ITEMS", new RarityWeight(builder, "ITEMS"));

        RUNED_ITEMS = builder.translation("mmorpg.word.runed_Item")
                .define("RUNED_ITEMS", new RarityWeight(builder, "RUNED_ITEMS"));

        RUNES = builder.translation("mmorpg.word.rune")
                .define("RUNES", new RarityWeight(builder, "RUNES"));

        MOBS = builder.translation("mmorpg.word.mob")
                .define("MOBS", new RarityWeight(builder, "MOBS"));

        MAPS = builder.translation("mmorpg.word.map")
                .define("MAPS", new RarityWeight(builder, "MAPS"));

        CURRENCY = builder.translation("mmorpg.word.currency")
                .define("CURRENCY", new RarityWeight(builder, "CURRENCY"));

        SPELLS = builder.translation("mmorpg.word.spell")
                .define("SPELLS", new RarityWeight(builder, "SPELLS"));

        builder.pop();
    }

}
