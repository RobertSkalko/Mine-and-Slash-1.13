package com.robertx22.database.stats.stat_mods.percent.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.pene.ThunderPene;

public class ThunderPenePercent extends BaseElePenePercent {

	@Override
	public Stat GetBaseStat() {
		return new ThunderPene();
	}

	@Override
	public String GUID() {
		return "ThunderPenePercent";
	}

}
