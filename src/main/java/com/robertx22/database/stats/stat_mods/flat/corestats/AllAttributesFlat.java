package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.core_stats.AllAttributes;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public class AllAttributesFlat extends StatMod {

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public Stat GetBaseStat() {
        return new AllAttributes();
    }

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 4;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "all_attributes_flat";
    }

}

