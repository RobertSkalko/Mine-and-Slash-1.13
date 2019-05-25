package com.robertx22.items.unique_items.bases;

import com.robertx22.database.gearitemslots.Bow;
import com.robertx22.items.gearitems.weapons.ItemBow;
import com.robertx22.items.unique_items.IUnique;

public abstract class BaseUniqueBow extends ItemBow implements IUnique {

    public BaseUniqueBow() {
        IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
        return new Bow().GUID();
    }

}
