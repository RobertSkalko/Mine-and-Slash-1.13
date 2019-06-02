package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalPene;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ElementalPenePercent extends ElementalStatMod {

    public ElementalPenePercent(Elements element) {
        super(element);
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalPenePercent(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalPene(this.element);
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 25;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public String GUID() {
        return element.name() + "PenePercent";
    }
}

