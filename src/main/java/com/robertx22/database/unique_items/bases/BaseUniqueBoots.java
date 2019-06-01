package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.armor.ItemBoots;

public abstract class BaseUniqueBoots extends ItemBoots implements IUnique {

    public BaseUniqueBoots() {
        super(new UniqueItem().Rank());

    }

    @Override
    public String slot() {
        return new Boots().GUID();
    }

}
