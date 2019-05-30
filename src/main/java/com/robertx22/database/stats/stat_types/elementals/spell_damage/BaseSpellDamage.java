package com.robertx22.database.stats.stat_types.elementals.spell_damage;

import com.robertx22.database.stats.Stat;

public abstract class BaseSpellDamage extends Stat {
    @Override
    public String locDescForLangFile() {
        return "Spell damage is used by spells and some other stats";
    }

    public BaseSpellDamage() {
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Spell " + this.Element().name() + " Damage";
    }
}
