package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.armor.ItemHelmet;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.rarities.items.UniqueItem;

public abstract class BaseUniqueHelmet extends ItemHelmet implements IUnique {

    public BaseUniqueHelmet() {
	super(new UniqueItem().Rank());

	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Helmet().GUID();
    }

}
