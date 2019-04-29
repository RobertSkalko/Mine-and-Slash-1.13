package com.robertx22.database.rarities.runed_items;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.items.UncommonItem;

public class UncommonRunedItem extends UncommonItem {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.RUNED_ITEMS.UNCOMMON_WEIGHT
                .get();

    }
}
