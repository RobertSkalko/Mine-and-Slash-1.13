package com.robertx22.config;

import com.robertx22.uncommon.enumclasses.EntitySystemChoice;

import static net.minecraftforge.common.ForgeConfigSpec.*;

public class ServerContainer {

    public static final String NAME = "SERVER";

    public BooleanValue USE_COMPATIBILITY_ITEMS;
    public BooleanValue DISABLE_VANILLA_HP_REGEN;
    public BooleanValue GENERATE_ORES;
    public BooleanValue LEVEL_UPS_COST_TOKEN;

    public IntValue MAXIMUM_WORN_RUNED_ITEMS;
    public IntValue MAXIMUM_WORN_UNIQUE_ITEMS;
    public IntValue MAX_PLAYERS_PER_MAP;
    public IntValue MAPS_DROP_AFTER_LEVEL;
    public IntValue CURRENCY_DROP_AFTER_LEVEL;
    public IntValue MAXIMUM_PLAYER_LEVEL;

    public DoubleValue NON_MOD_DAMAGE_MULTI;
    public DoubleValue MOB_ENVIRONMENT_DAMAGE_MULTI;
    public DoubleValue NON_MOD_HEAL_MULTI;
    public DoubleValue EXPERIENCE_MULTIPLIER;
    public DoubleValue UNARMED_ENERGY_COST;

    public ConfigValue<EntitySystemChoice> ENTITIES_UNDER_SYSTEM;

    ServerContainer(Builder builder) {
        builder.push("GENERAL");

        DISABLE_VANILLA_HP_REGEN = builder.comment(".")
                .translation("mmorpg.word.entities")
                .define("DISABLE_VANILLA_HP_REGEN", true);

        USE_COMPATIBILITY_ITEMS = builder.comment(".")
                .translation("mmorpg.word.")
                .define("USE_COMPATIBILITY_ITEMS", false);

        GENERATE_ORES = builder.comment(".")
                .translation("mmorpg.word.")
                .define("GENERATE_ORES", true);

        LEVEL_UPS_COST_TOKEN = builder.comment(".")
                .translation("mmorpg.word.")
                .define("LEVEL_UPS_COST_TOKEN", false);

        MAXIMUM_WORN_RUNED_ITEMS = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAXIMUM_WORN_RUNED_ITEMS", 3, 0, 100000);

        MAXIMUM_WORN_UNIQUE_ITEMS = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAXIMUM_WORN_UNIQUE_ITEMS", 3, 0, 100000);

        MAX_PLAYERS_PER_MAP = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAX_PLAYERS_PER_MAP", 5, 0, 100000);

        MAPS_DROP_AFTER_LEVEL = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAPS_DROP_AFTER_LEVEL", 15, 0, 100000);

        CURRENCY_DROP_AFTER_LEVEL = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("CURRENCY_DROP_AFTER_LEVEL", 10, 0, 100000);

        MAXIMUM_PLAYER_LEVEL = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MAXIMUM_PLAYER_LEVEL", 100, 0, 100000);

        NON_MOD_DAMAGE_MULTI = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("NON_MOD_DAMAGE_MULTI", 0.03D, 0D, 100000D);

        MOB_ENVIRONMENT_DAMAGE_MULTI = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("MOB_ENVIRONMENT_DAMAGE_MULTI", 0.2D, 0D, 100000D);

        NON_MOD_HEAL_MULTI = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("NON_MOD_HEAL_MULTI", 0.1D, 0D, 100000D);

        EXPERIENCE_MULTIPLIER = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("EXPERIENCE_MULTIPLIER", 1D, 0D, 100000D);

        UNARMED_ENERGY_COST = builder.comment(".")
                .translation("mmorpg.word.")
                .defineInRange("UNARMED_ENERGY_COST", 1D, 0D, 100000D);

        ENTITIES_UNDER_SYSTEM = builder.comment(".")
                .translation("mmorpg.word.")
                .define("ENTITIES_UNDER_SYSTEM", EntitySystemChoice.All_Entities);

        builder.pop();
    }

}
