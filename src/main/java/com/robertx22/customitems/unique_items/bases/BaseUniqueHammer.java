package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.weapons.ItemHammer;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Hammer;

public abstract class BaseUniqueHammer extends ItemHammer implements IUnique {

    public BaseUniqueHammer() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Hammer().GUID();
    }
}
