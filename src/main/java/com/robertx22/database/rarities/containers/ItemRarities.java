package com.robertx22.database.rarities.containers;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.database.rarities.items.*;

import java.util.Arrays;
import java.util.List;

public class ItemRarities extends RaritiesContainer<ItemRarity> {
    public static final List<ItemRarity> Items = Arrays.asList(new CommonItem(), new UncommonItem(), new RareItem(), new EpicItem(), new LegendaryItem(), new MythicalItem());

    @Override
    public List<ItemRarity> rarities() {
        return Items;
    }

    @Override
    public ItemRarity get(int i) {

        if (i == -1) {
            return new UniqueItem();
        }

        return super.get(i);

    }

}
