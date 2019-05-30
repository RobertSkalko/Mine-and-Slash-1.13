package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.items.gearitems.offhands.NormalShield;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Shield extends BaseOffHand {

    @Override
    public String GUID() {
        return "Shield";
    }

    @Override
    public Item DefaultItem() {
        return NormalShield.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return NormalShield.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Shield";
    }
}
