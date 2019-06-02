package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalSpellDamage;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ElementalSpellDamageMulti extends ElementalStatMod {

    public ElementalSpellDamageMulti() {
        super(Elements.None);
    }

    public ElementalSpellDamageMulti(Elements element) {
        super(element);
    }

    @Override
    public StatMod getStatMod(Elements element) {
        return new ElementalSpellDamageMulti(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellDamage(this.element);
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public String GUID() {
        return "Spell" + element.name() + "DamageMulti";
    }
}

