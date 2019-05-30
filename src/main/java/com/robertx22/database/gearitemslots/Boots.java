package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.BaseArmor;
import com.robertx22.items.gearitems.armor.ItemBoots;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Boots extends BaseArmor {

    @Override
    public String GUID() {
        return "Boots";
    }

    @Override
    public Item DefaultItem() {
        return ItemBoots.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemBoots.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Boots";
    }
}
