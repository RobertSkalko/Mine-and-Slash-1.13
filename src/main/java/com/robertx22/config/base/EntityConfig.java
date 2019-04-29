package com.robertx22.config.base;

import static net.minecraftforge.common.ForgeConfigSpec.Builder;
import static net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class EntityConfig {
    public DoubleValue LOOT_MULTI;
    public DoubleValue EXP_MULTI;

    public EntityConfig(String prefix, Builder builder, Double loot, Double exp) {
        builder.push(prefix);

        LOOT_MULTI = builder.translation("mmorpg.config.loot_multi")
                .defineInRange("_LOOT_MULTI", loot, 0D, 10000D);

        EXP_MULTI = builder.translation("mmorpg.config.exp_multi")
                .defineInRange("_EXP_MULTI", exp, 0D, 10000D);

        builder.pop();

    }

}
