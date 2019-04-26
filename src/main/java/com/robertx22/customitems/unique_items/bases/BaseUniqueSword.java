package com.robertx22.customitems.unique_items.bases;

import com.robertx22.customitems.gearitems.weapons.ItemSword;
import com.robertx22.customitems.unique_items.IUnique;
import com.robertx22.database.gearitemslots.Sword;

public abstract class BaseUniqueSword extends ItemSword implements IUnique {
    public BaseUniqueSword() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Sword().GUID();
    }
}
