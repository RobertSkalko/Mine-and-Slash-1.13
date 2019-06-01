package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.baubles.ItemCharm;

public abstract class BaseUniqueCharm extends ItemCharm implements IUnique {
    public BaseUniqueCharm() {

    }

    @Override
    public String slot() {
        return new Charm().GUID();
    }
}
