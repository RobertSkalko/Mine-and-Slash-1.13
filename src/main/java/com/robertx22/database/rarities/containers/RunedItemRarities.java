package com.robertx22.database.rarities.containers;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.database.rarities.runed_items.*;

import java.util.Arrays;
import java.util.List;

public class RunedItemRarities extends RaritiesContainer<ItemRarity> {

    public static final List<ItemRarity> RunedItems = Arrays.asList(new CommonRunedItem(), new UncommonRunedItem(), new RareRunedItem(), new EpicRunedItem(), new LegendaryRunedItem(), new MythicalRunedItem());

    @Override
    public List<ItemRarity> rarities() {
        return RunedItems;
    }
}

