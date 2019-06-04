package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Bow;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.items.gearitems.weapons.ItemBow;

public abstract class BaseUniqueBow extends ItemBow implements IUnique {

    public BaseUniqueBow() {
        super(new UniqueItem().Rank());
    }

    @Override
    public String slot() {
        return new Bow().GUID();
    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString() + ".desc";
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public Group locNameGroup() {
        return Group.Unique_Items;
    }
}
