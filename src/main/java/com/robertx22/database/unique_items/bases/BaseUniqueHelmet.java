package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.armor.ItemHelmet;

public abstract class BaseUniqueHelmet extends ItemHelmet implements IUnique {

    public BaseUniqueHelmet() {
        super(new UniqueItem().Rank());

    }

    @Override
    public String slot() {
        return new Helmet().GUID();
    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString() + ".desc";
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Unique_Items;
    }

}
