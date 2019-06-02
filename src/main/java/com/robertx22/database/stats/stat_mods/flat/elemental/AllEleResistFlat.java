package com.robertx22.database.stats.stat_mods.flat.elemental;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.elementals.AllEleResist;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class AllEleResistFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return new AllEleResist();
    }

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 12;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "all_ele_resist_flat";
    }

}
