package com.robertx22.database.stats.stat_mods.traits;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.bad_and_good.Barbarian;

public class BarbarianFlat extends BaseTraitMod {

	public BarbarianFlat() {
	}

	@Override
	public String GUID() {
		return "Barbarian";
	}

	@Override
	public Stat GetBaseStat() {
		return new Barbarian();
	}

}
