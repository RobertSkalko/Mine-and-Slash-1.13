package com.robertx22.database.stats.stat_mods.traits;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.bad_ones.Diseased;

public class DiseasedFlat extends BaseTraitMod {

	public DiseasedFlat() {
	}

	@Override
	public String GUID() {
		return "DiseasedFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Diseased();
	}

	@Override
	public int Weight() {
		return 10500;
	}
}
