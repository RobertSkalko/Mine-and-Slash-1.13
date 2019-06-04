package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.baubles.ItemNecklace;

public abstract class BaseUniqueNecklace extends ItemNecklace implements IUnique {
    public BaseUniqueNecklace() {
        super(new UniqueItem().Rank());
    }

    @Override
    public String slot() {
        return new Necklace().GUID();
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
