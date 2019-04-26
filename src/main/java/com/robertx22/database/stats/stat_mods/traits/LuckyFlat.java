package com.robertx22.database.stats.stat_mods.traits;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.Lucky;

public class LuckyFlat extends BaseTraitMod {

	public LuckyFlat() {
	}

	@Override
	public String GUID() {
		return "LuckyFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Lucky();
	}

}
