package com.robertx22.items.spells.aoe_bomb_proj;

import com.robertx22.items.spells.BaseSpellItem;

public abstract class BaseBombitem extends BaseSpellItem {

    public BaseBombitem() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return color + this.Spell().Element().name() + " Bomb";
    }
}