package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.weapons.ItemStaff;

public abstract class BaseUniqueStaff extends ItemStaff implements IUnique {

    public BaseUniqueStaff() {
        super(new UniqueItem().Rank());
    }

    @Override
    public String slot() {
        return new Staff().GUID();
    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }
}
