package com.robertx22.database.stats.stat_mods.flat.resources;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.resources.HealPower;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class HealPowerFlat extends StatMod {

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
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new HealPower();
    }

}
