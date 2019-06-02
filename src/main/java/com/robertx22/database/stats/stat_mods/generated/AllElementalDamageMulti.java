package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.AllElementalDamage;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class AllElementalDamageMulti extends ElementalStatMod {

    public AllElementalDamageMulti(Elements element) {
        super(element);
    }

    @Override
    public StatMod getStatMod(Elements element) {
        return new AllElementalDamageMulti(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new AllElementalDamage(this.element);
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public String GUID() {
        return "All" + element.name() + "DamageMulti";
    }
}


