package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Axe;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.weapons.ItemAxe;

public abstract class BaseUniqueAxe extends ItemAxe implements IUnique {

    public BaseUniqueAxe() {

    }

    @Override
    public String slot() {
        return new Axe().GUID();
    }

}
