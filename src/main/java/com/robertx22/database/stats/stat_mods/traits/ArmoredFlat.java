package com.robertx22.database.stats.stat_mods.traits;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.Armored;

public class ArmoredFlat extends BaseTraitMod {

	public ArmoredFlat() {
	}

	@Override
	public String GUID() {
		return "ArmoredFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Armored();
	}

}
