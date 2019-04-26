package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.baubles.ItemBracelet;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Bracelet;

public abstract class BaseUniqueBracelet extends ItemBracelet implements IUnique {

    public BaseUniqueBracelet() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Bracelet().GUID();
    }
}
