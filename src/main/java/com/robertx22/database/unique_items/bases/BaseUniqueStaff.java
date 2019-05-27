package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.UniqueItems;
import com.robertx22.items.gearitems.weapons.ItemStaff;

public abstract class BaseUniqueStaff extends ItemStaff implements IUnique {

    public BaseUniqueStaff() {
        UniqueItems.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
        return new Staff().GUID();
    }
}
