package com.robertx22.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public ConfigValue<EntityConfigs> EntityTypeConfig;
    public ConfigValue<RarityDropratesConfig> RarityWeightConfig;
    public ConfigValue<ServerContainer> Server;
    public ConfigValue<DropRatesContainer> DropRates;
    public ConfigValue<StatConfig> PlayerBaseStats;

    public static final String NAME = "MAIN_CONFIG";
    public static final ForgeConfigSpec spec;
    public static final ModConfig INSTANCE;

    static {
        final Pair<ModConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModConfig::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    ModConfig(ForgeConfigSpec.Builder builder) {

        builder.comment("Mine and Slash Config").push(NAME);

        EntityTypeConfig = builder.comment(".")
                .translation("mmorpg.word.entities")
                .define("EntityTypeConfig", new EntityConfigs(builder));

        PlayerBaseStats = builder.comment(".")
                .translation("mmorpg.word.base_player_stats")
                .define("PlayerBaseStats", new StatConfig(builder));

        Server = builder.comment(".")
                .translation("mmorpg.word.server")
                .define("Server", new ServerContainer(builder));

        DropRates = builder.comment(".")
                .translation("mmorpg.config.droprates")
                .define("DropRates", new DropRatesContainer(builder));

        RarityWeightConfig = builder.comment(".")
                .translation("mmorpg.config.rarity_weights")
                .define("RarityWeightConfig", new RarityDropratesConfig(builder));

        builder.pop();
        builder.build();
    }

}
