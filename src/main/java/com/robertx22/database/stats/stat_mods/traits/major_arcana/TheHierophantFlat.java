package com.robertx22.database.stats.stat_mods.traits.major_arcana;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.traits.major_arcana.TheHierophant;

public class TheHierophantFlat extends BaseMajorArcanaTrait {
    @Override
    public Stat GetBaseStat() {
        return new TheHierophant();
    }

    @Override
    public String GUID() {
        return "TheHierophantFlat";
    }
}
