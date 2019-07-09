package com.robertx22.database.rarities.mobs;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.rarities.base.BaseMythical;

public class MythicalMob extends BaseMythical implements MobRarity {

    @Override
    public float StatMultiplier() {
        return 4F;
    }

    @Override
    public float DamageMultiplier() {
        return 2.4F;
    }

    @Override
    public float HealthMultiplier() {
        return 7F;
    }

    @Override
    public float LootMultiplier() {
        return 9F;
    }

    @Override
    public int MaxMobEffects() {
        return 3;
    }

    @Override
    public float ExpOnKill() {
        return 75;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.MYTHICAL_WEIGHT.get();
    }

}
