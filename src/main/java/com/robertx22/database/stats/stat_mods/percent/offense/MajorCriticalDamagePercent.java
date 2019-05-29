package com.robertx22.database.stats.stat_mods.percent.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public class MajorCriticalDamagePercent extends StatMod {

    public MajorCriticalDamagePercent() {
    }

    @Override
    public String GUID() {
        return "MajorCriticalDamagePercent";
    }

    @Override
    public float Min() {
        return 15;

    }

    @Override
    public float Max() {
        return 30;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return new CriticalDamage();
    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

}
