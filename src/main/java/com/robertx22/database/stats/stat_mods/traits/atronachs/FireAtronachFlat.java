package com.robertx22.database.stats.stat_mods.traits.atronachs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.atronachs.FireAtronach;

public class FireAtronachFlat extends BaseTraitMod {

	public FireAtronachFlat() {
	}

	@Override
	public String GUID() {
		return "FireAtronachFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FireAtronach();
	}

}