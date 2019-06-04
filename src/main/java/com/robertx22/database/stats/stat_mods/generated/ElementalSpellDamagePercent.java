package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalSpellDamage;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ElementalSpellDamagePercent extends ElementalStatMod {

    public ElementalSpellDamagePercent(Elements element) {
        super(element);
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalSpellDamagePercent(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellDamage(this.element);
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 10;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public String GUID() {
        return "Spell" + element.name() + "DamagePercent";
    }
}
