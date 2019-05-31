package com.robertx22.database.stats.stat_mods.flat.elemental;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.elementals.all_damage.AllEleDmg;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class AllEleDmgFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return new AllEleDmg();
    }

    @Override
    public float Min() {
        return 4;
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
        return "AllEleDmgFlat";
    }

}
