package com.robertx22.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class DropRatesContainer {

    public DoubleValue RUNED_GEAR_DROPRATE;
    public DoubleValue RUNE_DROPRATE;
    public DoubleValue MAP_DROPRATE;
    public DoubleValue GEAR_DROPRATE;
    public DoubleValue UNIQUE_DROPRATE;
    public DoubleValue SPELL_DROPRATE;
    public DoubleValue CURRENCY_DROPRATE;
    public DoubleValue AWAKEN_RUNEWORD_DROPRATE;
    public DoubleValue COMPATIBLE_ITEMS_DROPRATE;

    DropRatesContainer(ForgeConfigSpec.Builder builder) {
        builder.push("DROPRATES");

        RUNED_GEAR_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.runed_gear_droprate")
                .defineInRange("RUNED_GEAR_DROPRATE", 1.8D, 0, 1000);

        RUNE_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.rune_droprate")
                .defineInRange("RUNE_DROPRATE", 1.4D, 0, 1000);

        MAP_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.map_droprate")
                .defineInRange("MAP_DROPRATE", 1F, 0, 1000);

        GEAR_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.gear_droprate")
                .defineInRange("GEAR_DROPRATE", 7.5F, 0, 1000);

        UNIQUE_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.unique_droprate")
                .defineInRange("UNIQUE_DROPRATE", 0.15F, 0, 1000);

        SPELL_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.spell_droprate")
                .defineInRange("SPELL_DROPRATE", 4F, 0, 1000);

        CURRENCY_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.currency_droprate")
                .defineInRange("CURRENCY_DROPRATE", 3F, 0, 1000);

        AWAKEN_RUNEWORD_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.awaken_runeword_droprate")
                .defineInRange("AWAKEN_RUNEWORD_DROPRATE", 0.25F, 0, 1000);

        COMPATIBLE_ITEMS_DROPRATE = builder.comment(".")
                .translation("mmorpg.config.compatible_items_droprate")
                .defineInRange("COMPATIBLE_ITEMS_DROPRATE", 3F, 0, 1000);

        builder.pop();

    }
}
