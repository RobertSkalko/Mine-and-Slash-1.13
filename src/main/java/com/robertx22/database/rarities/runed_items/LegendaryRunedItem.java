package com.robertx22.database.rarities.runed_items;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.items.LegendaryItem;

public class LegendaryRunedItem extends LegendaryItem {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.get().RUNED_ITEMS.get().
                LEGENDARY_WEIGHT.get();
    }
}
