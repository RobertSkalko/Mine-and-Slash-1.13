package com.robertx22.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

;

public class DropRatesContainer {

    public ConfigValue<Float> RUNED_GEAR_DROPRATE;
    public ConfigValue<Float> RUNE_DROPRATE;
    public ConfigValue<Float> MAP_DROPRATE;
    public ConfigValue<Float> GEAR_DROPRATE;
    public ConfigValue<Float> UNIQUE_DROPRATE;
    public ConfigValue<Float> SPELL_DROPRATE;
    public ConfigValue<Float> CURRENCY_DROPRATE;
    public ConfigValue<Float> AWAKEN_RUNEWORD_DROPRATE;
    public ConfigValue<Float> COMPATIBLE_ITEMS_DROPRATE;

    DropRatesContainer(ForgeConfigSpec.Builder builder) {
        builder.push("DROPRATES");

        RUNED_GEAR_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.runed_gear_droprate")
                .define("RUNED_GEAR_DROPRATE", 1.8F);

        RUNE_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.rune_droprate")
                .define("RUNE_DROPRATE", 1.4F);

        MAP_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.map_droprate")
                .define("MAP_DROPRATE", 1F);

        GEAR_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.gear_droprate")
                .define("GEAR_DROPRATE", 7.5F);

        UNIQUE_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.unique_droprate")
                .define("UNIQUE_DROPRATE", 0.15F);

        SPELL_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.spell_droprate")
                .define("SPELL_DROPRATE", 4F);

        CURRENCY_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.currency_droprate")
                .define("CURRENCY_DROPRATE", 3F);

        AWAKEN_RUNEWORD_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.awaken_runeword_droprate")
                .define("AWAKEN_RUNEWORD_DROPRATE", 0.25F);

        COMPATIBLE_ITEMS_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.compatible_items_droprate")
                .define("COMPATIBLE_ITEMS_DROPRATE", 3F);

        builder.pop();

    }
}
