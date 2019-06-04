package com.robertx22.items.spells.aoe_projectile;

import com.robertx22.items.spells.BaseSpellItem;

public abstract class BaseExplosionItem extends BaseSpellItem {

    public BaseExplosionItem() {
        super();

    }

    @Override
    public String locNameForLangFile() {
        return color + this.Spell().Element().name() + " Explosion";
    }
}