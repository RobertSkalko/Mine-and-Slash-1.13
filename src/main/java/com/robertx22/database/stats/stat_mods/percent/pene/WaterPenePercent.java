package com.robertx22.database.stats.stat_mods.percent.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.pene.WaterPene;

public class WaterPenePercent extends BaseElePenePercent {

	@Override
	public Stat GetBaseStat() {
		return new WaterPene();
	}

	@Override
	public String GUID() {
		return "WaterPenePercent";
	}

}