package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.IAmountsGenerated;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.StatModSizes;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public abstract class BaseCoreStatFlat extends StatMod implements IAmountsGenerated {

    public StatModSizes size = StatModSizes.Medium;

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    public BaseCoreStatFlat(StatModSizes size) {
        this.size = size;
    }

    @Override
    public float Min() {
        return 2 * this.size.multi;
    }

    @Override
    public float Max() {
        return 5 * this.size.multi;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public StatModSizes modSize() {
        return size;
    }

}
