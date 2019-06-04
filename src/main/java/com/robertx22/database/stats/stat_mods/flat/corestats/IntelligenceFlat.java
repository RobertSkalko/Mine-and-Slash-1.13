package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.core_stats.Intelligence;

public class IntelligenceFlat extends BaseCoreStatFlat {

    public IntelligenceFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return new Intelligence();
    }

    @Override
    public String GUID() {
        return "IntelligenceFlat";
    }
}
