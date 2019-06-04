package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.weapons.ItemSword;

public abstract class BaseUniqueSword extends ItemSword implements IUnique {
    public BaseUniqueSword() {
        super(new UniqueItem().Rank());
    }

    @Override
    public String slot() {
        return new Sword().GUID();
    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }
}
