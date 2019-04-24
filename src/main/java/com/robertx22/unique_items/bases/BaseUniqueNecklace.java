package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.baubles.ItemNecklace;
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.unique_items.IUnique;

public abstract class BaseUniqueNecklace extends ItemNecklace implements IUnique {
    public BaseUniqueNecklace() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Necklace().GUID();
    }
}
