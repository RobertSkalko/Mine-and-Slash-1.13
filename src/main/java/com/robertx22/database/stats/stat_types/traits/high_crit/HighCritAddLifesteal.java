package com.robertx22.database.stats.stat_types.traits.high_crit;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.stat_types.traits.bases.BaseTraitHighCritHit;

import java.util.Arrays;
import java.util.List;

public class HighCritAddLifesteal extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new LifestealFlat());

    }

    @Override
    public String GUID() {
        return "HighCritAddLifesteal";
    }

}
