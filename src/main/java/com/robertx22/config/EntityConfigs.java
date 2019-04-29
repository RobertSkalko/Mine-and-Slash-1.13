package com.robertx22.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import org.apache.commons.lang3.tuple.Pair;

public class EntityConfigs {

    public EntityConfig NPC_CONFIG;
    public EntityConfig MOB_CONFIG;
    public EntityConfig ANIMAL_CONFIG;
    public EntityConfig OTHER_CONFIG;

    public static final String NAME = "MAIN_CONFIG";
    public static final ForgeConfigSpec spec;
    public static final EntityConfigs INSTANCE;

    static {
        final Pair<EntityConfigs, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
                .configure(EntityConfigs::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    public EntityConfigs(ForgeConfigSpec.Builder builder) {
        builder.push("entity_configs");

        NPC_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig(builder1, 0.3D, 0.3D))
                .getLeft();

        MOB_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig(builder1, 1D, 1D))
                .getLeft();

        ANIMAL_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig(builder1, 0.01D, 0.1D))
                .getLeft();

        OTHER_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig(builder1, 0D, 0D))
                .getLeft();

        builder.pop();

    }

    public static class EntityConfig {

        public DoubleValue LOOT_MULTI;
        public DoubleValue EXP_MULTI;

        public EntityConfig(ForgeConfigSpec.Builder builder, Double loot, Double exp) {
            builder.push("entity_type");

            LOOT_MULTI = builder.translation("mmorpg.config.loot_multi")
                    .defineInRange("_LOOT_MULTI", loot, 0D, 10000D);

            EXP_MULTI = builder.translation("mmorpg.config.exp_multi")
                    .defineInRange("_EXP_MULTI", exp, 0D, 10000D);

            builder.pop();

        }

    }
}
