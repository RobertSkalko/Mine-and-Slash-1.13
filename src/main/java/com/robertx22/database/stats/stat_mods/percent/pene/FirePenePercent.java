package com.robertx22.database.stats.stat_mods.percent.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.pene.FirePene;

public class FirePenePercent extends BaseElePenePercent {

	@Override
	public Stat GetBaseStat() {
		return new FirePene();

	}

	@Override
	public String GUID() {
		return "FirePenePercent";
	}

}
