package com.robertx22.config;

import com.robertx22.uncommon.enumclasses.EntitySystemChoice;
import net.minecraftforge.common.ForgeConfigSpec;

public class ServerContainer {

    public static final String NAME = "SERVER";

    public boolean USE_COMPATIBILITY_ITEMS = false;
    public ForgeConfigSpec.BooleanValue DISABLE_VANILLA_HP_REGEN;
    public int MAXIMUM_WORN_RUNED_ITEMS = 3;
    public int MAXIMUM_WORN_UNIQUE_ITEMS = 3;
    public boolean GENERATE_ORES = true;
    public float UNARMED_ENERGY_COST = 1;
    public int MAPS_DROP_AFTER_LEVEL = 20;
    public int CURRENCY_DROP_AFTER_LEVEL = 10;
    public int MAXIMUM_PLAYER_LEVEL = 100;
    public float NON_MOD_DAMAGE_MULTI = 0.03F;
    public float MOB_ENVIRONMENT_DAMAGE_MULTI = 0.2F;
    public float NON_MOD_HEAL_MULTI = 0.1F;
    public float EXPERIENCE_MULTIPLIER = 1F;
    public boolean LEVEL_UPS_COST_TOKEN = false;
    public int MAX_PLAYERS_PER_MAP = 5;
    public ConfigValue<EntitySystemChoice> ENTITIES_UNDER_SYSTEM = EntitySystemChoice.All_Entities;

    ServerContainer(ForgeConfigSpec.Builder builder) {
        builder.comment("Client Settings").push(NAME);

        DISABLE_VANILLA_HP_REGEN = builder.comment(".")
                .translation("mmorpg.word.entities")
                .define("DISABLE_VANILLA_HP_REGEN", true);

        builder.pop();
        builder.build();
    }

}
