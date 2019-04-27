package com.robertx22.database.stats.stat_mods.percent.less;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class LessCriticalHitPercent extends StatMod {

    public LessCriticalHitPercent() {
    }

    @Override
    public String GUID() {
	return "LessCriticalHitPercent";

    }

    @Override
    public float Min() {
	return -10;
    }

    @Override
    public float Max() {
	return -20;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
	return new CriticalHit();
    }

}