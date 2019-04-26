package com.robertx22.database.stats.stat_mods.traits.conditionals.high_crit;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.high_crit.HighCritAddLifesteal;

public class HighCritAddLifestealFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "HighCritAddLifestealFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new HighCritAddLifesteal();

    }

}
