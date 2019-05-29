package com.robertx22.database.stats.stat_types.traits.low_dodge;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_types.traits.bases.BaseTraitLowDodge;

import java.util.Arrays;
import java.util.List;

public class LowDodgeAddCritHit extends BaseTraitLowDodge {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CriticalHitFlat());

    }

    @Override
    public String GUID() {
        return "LowDodgeAddCritHit";
    }

}
