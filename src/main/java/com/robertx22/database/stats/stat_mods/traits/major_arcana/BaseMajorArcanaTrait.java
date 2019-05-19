package com.robertx22.database.stats.stat_mods.traits.major_arcana;

import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.uncommon.interfaces.IWeighted;

public abstract class BaseMajorArcanaTrait extends BaseTraitMod {

    @Override
    public int Weight() {
        return IWeighted.MythicWeight;
    }
    
}
