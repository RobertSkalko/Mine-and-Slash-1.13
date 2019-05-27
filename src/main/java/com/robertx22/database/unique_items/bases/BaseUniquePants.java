package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.UniqueItems;
import com.robertx22.items.gearitems.armor.ItemPants;

public abstract class BaseUniquePants extends ItemPants implements IUnique {

    public BaseUniquePants() {
        super(new UniqueItem().Rank());
        UniqueItems.ITEMS.put(GUID(), this);

    }

    @Override
    public String slot() {
        return new Pants().GUID();
    }

}
