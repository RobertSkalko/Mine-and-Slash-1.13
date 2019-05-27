package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.UniqueItems;
import com.robertx22.items.gearitems.baubles.ItemCharm;

public abstract class BaseUniqueCharm extends ItemCharm implements IUnique {
    public BaseUniqueCharm() {
        UniqueItems.ITEMS.put(GUID(), this);

    }

    @Override
    public String slot() {
        return new Charm().GUID();
    }
}
