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
                .define("NPC_CONFIG", new EntityConfig("npc", builder, 0.3D, 0.3D));

        MOB_CONFIG = builder.translation("mmorpg.config.mob")
                .define("MOB_CONFIG", new EntityConfig("mob", builder, 1.05D, 1.05D));

        ANIMAL_CONFIG = builder.translation("mmorpg.config.animal")
                .define("ANIMAL_CONFIG", new EntityConfig("animal", builder, 0.01D, 0.05D));

        OTHER_CONFIG = builder.translation("mmorpg.config.other")
                .define("OTHER_CONFIG", new EntityConfig("other", builder, 0D, 0D));

        builder.pop();
        builder.build();

    }

    public static class EntityConfig {

        public DoubleValue LOOT_MULTI;
        public DoubleValue EXP_MULTI;

        public EntityConfig(String type, ForgeConfigSpec.Builder builder, Double loot,
                            Double exp) {

            LOOT_MULTI = builder.translation("mmorpg.config.loot_multi")
                    .defineInRange(type + "_LOOT_MULTI", loot, 0D, 10000D);

            EXP_MULTI = builder.translation("mmorpg.config.exp_multi")
                    .defineInRange(type + "_EXP_MULTI", exp, 0D, 10000D);

            builder.pop();
            builder.build();

        }

    }
}
