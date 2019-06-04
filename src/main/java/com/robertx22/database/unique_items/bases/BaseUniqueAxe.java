package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Axe;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.weapons.ItemAxe;

public abstract class BaseUniqueAxe extends ItemAxe implements IUnique {

    public BaseUniqueAxe() {
        super(new UniqueItem().Rank());
    }

    @Override
    public String slot() {
        return new Axe().GUID();
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
