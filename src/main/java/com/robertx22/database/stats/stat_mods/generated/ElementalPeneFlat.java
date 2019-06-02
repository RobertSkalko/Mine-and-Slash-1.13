package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalPene;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ElementalPeneFlat extends ElementalStatMod {

    public ElementalPeneFlat(Elements element) {
        super(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalPene(this.element);
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
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return element.name() + "PeneFlat";
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalPeneFlat(element);
    }
}
