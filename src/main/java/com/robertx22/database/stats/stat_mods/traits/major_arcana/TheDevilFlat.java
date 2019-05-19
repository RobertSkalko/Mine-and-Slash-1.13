package com.robertx22.database.stats.stat_mods.traits.major_arcana;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.traits.major_arcana.TheDevil;

public class TheDevilFlat extends BaseMajorArcanaTrait {
    @Override
    public Stat GetBaseStat() {
        return new TheDevil();
    }

    @Override
    public String GUID() {
        return "TheDevilFlat";
    }
}
