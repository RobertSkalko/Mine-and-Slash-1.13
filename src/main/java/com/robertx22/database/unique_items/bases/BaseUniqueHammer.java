package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Hammer;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.weapons.ItemHammer;

public abstract class BaseUniqueHammer extends ItemHammer implements IUnique {

    public BaseUniqueHammer() {

    }

    @Override
    public String slot() {
        return new Hammer().GUID();
    }
}
