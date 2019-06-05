package com.robertx22.database.unique_items.bases;

import com.robertx22.database.gearitemslots.Shield;
import com.robertx22.database.unique_items.IUnique;
import net.minecraft.item.ItemShield;

public abstract class BaseUniqueShield extends ItemShield implements IUnique {

    public BaseUniqueShield() {
        super(new Properties().maxStackSize(1).defaultMaxDamage(1000));

    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString() + ".desc";
    }

    @Override
    public String slot() {
        return new Shield().GUID();
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Unique_Items;
    }

}

