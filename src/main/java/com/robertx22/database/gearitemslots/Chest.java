package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.BaseArmor;
import com.robertx22.items.gearitems.armor.ItemChest;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Chest extends BaseArmor {

    @Override
    public String GUID() {
        return "Chest";
    }

    @Override
    public Item DefaultItem() {
        return ItemChest.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemChest.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Chest";
    }
}
