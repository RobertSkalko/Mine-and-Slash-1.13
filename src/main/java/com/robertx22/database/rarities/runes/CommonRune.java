package com.robertx22.database.rarities.runes;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.rarities.items.CommonItem;

public class CommonRune extends CommonItem implements RuneRarity {

    @Override
    public int minimumRunewordPower() {
        return 1;
    }

    @Override
    public int maximumRunewordPower() {
        return 1;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.get().RUNES.get().COMMON_WEIGHT.get();
    }
}
