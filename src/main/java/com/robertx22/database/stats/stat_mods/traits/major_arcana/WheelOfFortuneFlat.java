package com.robertx22.database.stats.stat_mods.traits.major_arcana;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.traits.major_arcana.WheelOfFortune;

public class WheelOfFortuneFlat extends BaseMajorArcanaTrait {
    @Override
    public Stat GetBaseStat() {
        return new WheelOfFortune();
    }

    @Override
    public String GUID() {
        return "WheelOfFortuneFlat";
    }
}
