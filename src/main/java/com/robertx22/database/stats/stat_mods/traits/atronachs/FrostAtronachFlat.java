package com.robertx22.database.stats.stat_mods.traits.atronachs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.atronachs.FrostAtronach;

public class FrostAtronachFlat extends BaseTraitMod {

	public FrostAtronachFlat() {
	}

	@Override
	public String GUID() {
		return "FrostAtronachFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FrostAtronach();
	}

}