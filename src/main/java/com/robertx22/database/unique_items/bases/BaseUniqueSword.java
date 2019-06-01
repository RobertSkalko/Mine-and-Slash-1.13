package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.weapons.ItemSword;

public abstract class BaseUniqueSword extends ItemSword implements IUnique {
    public BaseUniqueSword() {

    }

    @Override
    public String slot() {
        return new Sword().GUID();
    }
}
