package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.baubles.ItemRing;

public abstract class BaseUniqueRing extends ItemRing implements IUnique {
    public BaseUniqueRing() {
    }

    @Override
    public String slot() {
        return new Ring().GUID();
    }
}
