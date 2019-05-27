package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.UniqueItems;
import com.robertx22.items.gearitems.baubles.ItemBracelet;

public abstract class BaseUniqueBracelet extends ItemBracelet implements IUnique {

    public BaseUniqueBracelet() {
        UniqueItems.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
        return new Bracelet().GUID();
    }
}
