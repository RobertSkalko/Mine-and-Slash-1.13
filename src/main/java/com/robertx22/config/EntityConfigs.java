package com.robertx22.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import org.apache.commons.lang3.tuple.Pair;

public class EntityConfigs {

    public ConfigValue<EntityConfig> NPC_CONFIG;
    public ConfigValue<EntityConfig> MOB_CONFIG;
    public ConfigValue<EntityConfig> ANIMAL_CONFIG;
    public ConfigValue<EntityConfig> OTHER_CONFIG;

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

        NPC_CONFIG = builder.translation("mmorpg.config.npc")
                .define("NPC_CONFIG", new EntityConfig("npc", 0.3D, 0.3D));

        MOB_CONFIG = builder.translation("mmorpg.config.mob")
                .define("MOB_CONFIG", new EntityConfig("mob", 1.05D, 1.05D));

        ANIMAL_CONFIG = builder.translation("mmorpg.config.animal")
                .define("ANIMAL_CONFIG", new EntityConfig("animal", 0.01D, 0.05D));

        OTHER_CONFIG = builder.translation("mmorpg.config.other")
                .define("OTHER_CONFIG", new EntityConfig("other", 0D, 0D));

        builder.pop();

    }

    public static class EntityConfig {

        public DoubleValue LOOT_MULTI;
        public DoubleValue EXP_MULTI;

        public EntityConfig(String type, Double loot, Double exp) {
            ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

            LOOT_MULTI = builder.translation("mmorpg.config.loot_multi")
                    .defineInRange(type + "_LOOT_MULTI", loot, 0D, 10000D);

            EXP_MULTI = builder.translation("mmorpg.config.exp_multi")
                    .defineInRange(type + "_EXP_MULTI", exp, 0D, 10000D);

            builder.pop();

        }

    }
}
