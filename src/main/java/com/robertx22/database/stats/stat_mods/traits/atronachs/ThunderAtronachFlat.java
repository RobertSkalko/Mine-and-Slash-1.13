package com.robertx22.database.stats.stat_mods.traits.atronachs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.atronachs.ThunderAtronach;

public class ThunderAtronachFlat extends BaseTraitMod {

	public ThunderAtronachFlat() {
	}

	@Override
	public String GUID() {
		return "ThunderAtronachFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderAtronach();
	}

}