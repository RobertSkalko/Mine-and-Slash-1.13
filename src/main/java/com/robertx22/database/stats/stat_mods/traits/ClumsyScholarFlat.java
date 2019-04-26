package com.robertx22.database.stats.stat_mods.traits;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.bad_and_good.ClumsyScholar;

public class ClumsyScholarFlat extends BaseTraitMod {

	public ClumsyScholarFlat() {
	}

	@Override
	public String GUID() {
		return "ClumsyScholar";
	}

	@Override
	public Stat GetBaseStat() {
		return new ClumsyScholar();
	}

}