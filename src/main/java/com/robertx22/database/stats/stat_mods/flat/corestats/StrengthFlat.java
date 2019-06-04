package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.core_stats.Strength;

public class StrengthFlat extends BaseCoreStatFlat {

    public StrengthFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return new Strength();
    }

    @Override
    public String GUID() {
        return "StrengthFlat";
    }
}
