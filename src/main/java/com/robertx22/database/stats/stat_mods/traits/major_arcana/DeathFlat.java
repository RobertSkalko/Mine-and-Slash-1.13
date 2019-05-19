package com.robertx22.database.stats.stat_mods.traits.major_arcana;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.traits.major_arcana.Death;

public class DeathFlat extends BaseMajorArcanaTrait {
    @Override
    public Stat GetBaseStat() {
        return new Death();
    }

    @Override
    public String GUID() {
        return "DeathFlat";
    }
}
