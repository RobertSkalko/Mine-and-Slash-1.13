package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.baubles.ItemRing;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Ring;

public abstract class BaseUniqueRing extends ItemRing implements IUnique {
    public BaseUniqueRing() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Ring().GUID();
    }
}
