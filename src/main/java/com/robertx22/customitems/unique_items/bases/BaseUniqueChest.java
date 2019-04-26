package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.armor.ItemChest;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.rarities.items.UniqueItem;

public abstract class BaseUniqueChest extends ItemChest implements IUnique {

    public BaseUniqueChest() {
	super(new UniqueItem().Rank());
	IUnique.ITEMS.put(GUID(), this);

    }

    @Override
    public String slot() {
	return new Chest().GUID();
    }

}
