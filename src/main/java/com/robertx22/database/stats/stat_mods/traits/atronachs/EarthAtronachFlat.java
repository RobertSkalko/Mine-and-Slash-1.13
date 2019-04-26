package com.robertx22.database.stats.stat_mods.traits.atronachs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.atronachs.EarthAtronach;

public class EarthAtronachFlat extends BaseTraitMod {

	public EarthAtronachFlat() {
	}

	@Override
	public String GUID() {
		return "EarthAtronachFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new EarthAtronach();
	}

}