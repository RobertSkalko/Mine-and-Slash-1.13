package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.armor.ItemPants;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.rarities.items.UniqueItem;

public abstract class BaseUniquePants extends ItemPants implements IUnique {

    public BaseUniquePants() {
	super(new UniqueItem().Rank());
	IUnique.ITEMS.put(GUID(), this);

    }

    @Override
    public String slot() {
	return new Pants().GUID();
    }

}
