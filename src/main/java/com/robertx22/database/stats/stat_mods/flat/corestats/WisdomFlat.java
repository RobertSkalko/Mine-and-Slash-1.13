package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.core_stats.Wisdom;

public class WisdomFlat extends BaseCoreStatFlat {
    @Override
    public Stat GetBaseStat() {
        return new Wisdom();
    }

    @Override
    public String GUID() {
        return "WisdomFlat";
    }
}