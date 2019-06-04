package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.baubles.ItemBracelet;

public abstract class BaseUniqueBracelet extends ItemBracelet implements IUnique {

    public BaseUniqueBracelet() {
        super(new UniqueItem().Rank());
    }

    @Override
    public String slot() {
        return new Bracelet().GUID();
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
