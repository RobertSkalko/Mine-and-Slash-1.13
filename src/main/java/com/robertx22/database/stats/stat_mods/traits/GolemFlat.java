package com.robertx22.database.stats.stat_mods.traits;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.Golem;

public class GolemFlat extends BaseTraitMod {

	public GolemFlat() {
	}

	@Override
	public String GUID() {
		return "GolemFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Golem();
	}

}
