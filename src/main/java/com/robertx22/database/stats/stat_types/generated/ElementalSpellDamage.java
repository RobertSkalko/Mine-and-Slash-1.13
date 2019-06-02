package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.ElementalStat;
import com.robertx22.uncommon.enumclasses.Elements;

public class ElementalSpellDamage extends ElementalStat {

    public ElementalSpellDamage() {
        super(Elements.None);
    }

    public ElementalSpellDamage(Elements element) {
        super(element);
    }

    @Override
    public Stat newStatInstance(Elements element) {
        return new ElementalSpellDamage(element);
    }

    @Override
    public String GUID() {
        return "Spell " + this.Element().name() + " Damage";
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

    @Override
    public String locDescForLangFile() {
        return "Spell damage is used by spells and some other stats";
    }

}

