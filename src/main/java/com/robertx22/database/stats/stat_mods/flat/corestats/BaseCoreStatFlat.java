package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public abstract class BaseCoreStatFlat extends StatMod {

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 5;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

}
