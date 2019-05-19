package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.core_stats.Vitality;

public class VitalityFlat extends BaseCoreStatFlat {
    @Override
    public Stat GetBaseStat() {
        return new Vitality();
    }

    @Override
    public String GUID() {
        return "VitalityFlat";
    }
}
