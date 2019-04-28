package com.robertx22.database.rarities.runed_items;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.items.RareItem;

public class RareRunedItem extends RareItem {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.get().RUNED_ITEMS.get().RARE_WEIGHT.get();
    }
}
