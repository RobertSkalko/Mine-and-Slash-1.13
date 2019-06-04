package com.robertx22.items.spells.projectile;

import com.robertx22.items.spells.BaseSpellItem;

public abstract class BaseBoltItem extends BaseSpellItem {

    public BaseBoltItem() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return this.Spell().Element().name() + " Bolt";
    }
}