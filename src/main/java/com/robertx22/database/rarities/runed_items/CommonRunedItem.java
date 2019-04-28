package com.robertx22.database.rarities.runed_items;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.items.CommonItem;

public class CommonRunedItem extends CommonItem {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.get().RUNED_ITEMS.get().COMMON_WEIGHT
                .get();
    }
}
