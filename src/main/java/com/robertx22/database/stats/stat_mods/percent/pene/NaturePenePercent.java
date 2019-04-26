package com.robertx22.database.stats.stat_mods.percent.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.pene.NaturePene;

public class NaturePenePercent extends BaseElePenePercent {

	@Override
	public Stat GetBaseStat() {
		return new NaturePene();
	}

	@Override
	public String GUID() {
		return "NaturePenePercent";
	}

}
