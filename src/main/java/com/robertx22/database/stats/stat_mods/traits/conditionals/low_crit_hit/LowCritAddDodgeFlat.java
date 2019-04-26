package com.robertx22.database.stats.stat_mods.traits.conditionals.low_crit_hit;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.low_crit_hit.LowCritHitAddHealth;

public class LowCritAddDodgeFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "LowCritAddDodgeFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LowCritHitAddHealth();
    }

}
