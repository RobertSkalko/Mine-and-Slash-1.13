package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.baubles.ItemNecklace;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Necklace;

public abstract class BaseUniqueNecklace extends ItemNecklace implements IUnique {
    public BaseUniqueNecklace() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Necklace().GUID();
    }
}
