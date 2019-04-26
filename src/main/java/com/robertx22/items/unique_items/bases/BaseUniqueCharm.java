package com.robertx22.items.unique_items.bases;

import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.items.gearitems.baubles.ItemCharm;
import com.robertx22.items.unique_items.IUnique;

public abstract class BaseUniqueCharm extends ItemCharm implements IUnique {
    public BaseUniqueCharm() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Charm().GUID();
    }
}
