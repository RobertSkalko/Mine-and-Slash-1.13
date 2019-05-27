package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Hammer;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.UniqueItems;
import com.robertx22.items.gearitems.weapons.ItemHammer;

public abstract class BaseUniqueHammer extends ItemHammer implements IUnique {

    public BaseUniqueHammer() {
        UniqueItems.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
        return new Hammer().GUID();
    }
}
