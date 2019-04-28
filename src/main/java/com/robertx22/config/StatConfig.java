package com.robertx22.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class StatConfig {

    public static final String NAME = "BASE_STATS";

    public ConfigValue<Float> physical_damage;
    public ConfigValue<Float> physical_damage_per_level;
    public ConfigValue<Float> mana;
    public ConfigValue<Float> mana_per_level;
    public ConfigValue<Float> energy;
    public ConfigValue<Float> energy_per_level;
    public ConfigValue<Float> mana_regen;
    public ConfigValue<Float> mana_regen_per_level;
    public ConfigValue<Float> health_regen;
    public ConfigValue<Float> health_regen_per_level;
    public ConfigValue<Float> armor;
    public ConfigValue<Float> armor_per_level;
    public ConfigValue<Float> health;
    public ConfigValue<Float> health_per_level;
    ;
    public ConfigValue<Float> critical_hit;
    public ConfigValue<Float> critical_hit_per_level;
    public ConfigValue<Float> energy_regen;
    public ConfigValue<Float> energy_regen_per_level;
    public ConfigValue<Float> critical_damage;
    public ConfigValue<Float> critical_damage_per_level;

    StatConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Player Base Stats").push(NAME);

        physical_damage = builder.translation("mmorpg.stat.physical_damage")
                .define("physical_damage", 3F);
        physical_damage_per_level = builder.translation("mmorpg.stat.physical_damage_per_level")
                .define("physical_damage_per_level", 0.3F);
        mana = builder.translation("mmorpg.stat.mana").define("mana", 50F);
        mana_per_level = builder.translation("mmorpg.stat.mana_per_level")
                .define("mana_per_level", 0F);
        physical_damage = builder.translation("mmorpg.stat.energy")
                .define("energy", 100F);
        physical_damage = builder.translation("mmorpg.stat.energy_per_level")
                .define("energy_per_level", 0F);
        physical_damage = builder.translation("mmorpg.stat.mana_regen")
                .define("mana_regen", 3F);
        physical_damage = builder.translation("mmorpg.stat.mana_regen_per_level")
                .define("mana_regen_per_level", 0F);

        physical_damage = builder.translation("mmorpg.stat.health_regen")
                .define("health_regen", 5F);
        physical_damage = builder.translation("mmorpg.stat.health_regen_per_level")
                .define("health_regen_per_level", 1F);
        physical_damage = builder.translation("mmorpg.stat.armor").define("armor", 10F);
        physical_damage = builder.translation("mmorpg.stat.armor_per_level")
                .define("armor_per_level", 5F);
        physical_damage = builder.translation("mmorpg.stat.health")
                .define("health", 100F);
        physical_damage = builder.translation("mmorpg.stat.health_per_level")
                .define("health_per_level", 10F);
        physical_damage = builder.translation("mmorpg.stat.critical_hit")
                .define("critical_hit", 1F);
        physical_damage = builder.translation("mmorpg.stat.critical_hit_per_level")
                .define("critical_hit_per_level", 0F);
        physical_damage = builder.translation("mmorpg.stat.energy_regen")
                .define("energy_regen", 5F);
        physical_damage = builder.translation("mmorpg.stat.energy_regen_per_level")
                .define("energy_regen_per_level", 0F);
        physical_damage = builder.translation("mmorpg.stat.critical_damage")
                .define("critical_damage", 0F);
        physical_damage = builder.translation("mmorpg.stat.critical_damage_per_level")
                .define("critical_damage_per_level", 0F);

        builder.pop();
        builder.build();

    }
}