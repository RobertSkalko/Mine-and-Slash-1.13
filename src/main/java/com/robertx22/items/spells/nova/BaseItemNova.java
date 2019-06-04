package com.robertx22.items.spells.nova;

import com.robertx22.items.spells.BaseSpellItem;

public abstract class BaseItemNova extends BaseSpellItem {

    public BaseItemNova() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return this.Spell().Element().name() + " Nova";
    }
}