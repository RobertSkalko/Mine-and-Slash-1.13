package com.robertx22.database.rarities.runed_items;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.items.EpicItem;

public class EpicRunedItem extends EpicItem {

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.get().RUNED_ITEMS.get().EPIC_WEIGHT.get();
    }
}
