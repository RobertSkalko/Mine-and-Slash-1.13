package com.robertx22.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class EntityConfigs {

    public ConfigValue<EntityConfig> NPC_CONFIG;
    public ConfigValue<EntityConfig> MOB_CONFIG;
    public ConfigValue<EntityConfig> ANIMAL_CONFIG;
    public ConfigValue<EntityConfig> OTHER_CONFIG;

    public EntityConfigs(ForgeConfigSpec.Builder builder) {

        NPC_CONFIG = builder.translation("mmorpg.config.npc")
                .define("NPC_CONFIG", new EntityConfig(builder, 0.3D, 0.3D));

        MOB_CONFIG = builder.translation("mmorpg.config.mob")
                .define("MOB_CONFIG", new EntityConfig(builder, 1D, 1D));

        ANIMAL_CONFIG = builder.translation("mmorpg.config.animal")
                .define("ANIMAL_CONFIG", new EntityConfig(builder, 0.01D, 0.05D));

        OTHER_CONFIG = builder.translation("mmorpg.config.other")
                .define("OTHER_CONFIG", new EntityConfig(builder, 0D, 0D));

    }

    public static class EntityConfig {

        public DoubleValue LOOT_MULTI;
        public DoubleValue EXP_MULTI;

        public EntityConfig(ForgeConfigSpec.Builder builder, Double loot, Double exp) {

            LOOT_MULTI = builder.translation("mmorpg.config.runed_gear_droprate")
                    .defineInRange("LOOT_MULTI", loot, 0, 10000);

            EXP_MULTI = builder.translation("mmorpg.config.runed_gear_droprate")
                    .defineInRange("EXP_MULTI", exp, 0, 10000);

            builder.pop();
            builder.build();

        }

    }
}
