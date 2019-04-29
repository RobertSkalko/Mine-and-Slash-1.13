package com.robertx22.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class EntityConfigs {

    public EntityConfig NPC_CONFIG;
    public EntityConfig MOB_CONFIG;
    public EntityConfig ANIMAL_CONFIG;
    public EntityConfig OTHER_CONFIG;

    public EntityConfigs(ForgeConfigSpec.Builder builder) {
        builder.push("entity_configs");

        NPC_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig("NPC", builder1, 0.3D, 0.3D))
                .getLeft();

        MOB_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig("MOB", builder1, 1D, 1D))
                .getLeft();

        ANIMAL_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig("ANIMAL", builder1, 0.01D, 0.1D))
                .getLeft();

        OTHER_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig("OTHER", builder1, 0D, 0D))
                .getLeft();

        builder.pop();

    }

    public static class EntityConfig {

        public DoubleValue LOOT_MULTI;
        public DoubleValue EXP_MULTI;

        public EntityConfig(String prefix, ForgeConfigSpec.Builder builder, Double loot,
                            Double exp) {
            builder.push(prefix + "_entity_type");

            LOOT_MULTI = builder.translation("mmorpg.config.loot_multi")
                    .defineInRange("_LOOT_MULTI", loot, 0D, 10000D);

            EXP_MULTI = builder.translation("mmorpg.config.exp_multi")
                    .defineInRange("_EXP_MULTI", exp, 0D, 10000D);

            builder.pop();

        }

    }
}
