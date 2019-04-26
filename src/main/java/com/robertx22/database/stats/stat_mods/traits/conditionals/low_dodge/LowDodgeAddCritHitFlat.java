package com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.low_dodge.LowDodgeAddCritHit;

public class LowDodgeAddCritHitFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "LowDodgeAddCritHit";
    }

    @Override
    public Stat GetBaseStat() {
	return new LowDodgeAddCritHit();
    }

}
