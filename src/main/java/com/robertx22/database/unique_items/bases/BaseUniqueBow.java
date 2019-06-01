package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Bow;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.weapons.ItemBow;

public abstract class BaseUniqueBow extends ItemBow implements IUnique {

    public BaseUniqueBow() {
    }

    @Override
    public String slot() {
        return new Bow().GUID();
    }

}
