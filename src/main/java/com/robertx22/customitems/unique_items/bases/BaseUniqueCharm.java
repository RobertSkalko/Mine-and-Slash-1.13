package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.baubles.ItemCharm;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Charm;

public abstract class BaseUniqueCharm extends ItemCharm implements IUnique {
    public BaseUniqueCharm() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Charm().GUID();
    }
}
