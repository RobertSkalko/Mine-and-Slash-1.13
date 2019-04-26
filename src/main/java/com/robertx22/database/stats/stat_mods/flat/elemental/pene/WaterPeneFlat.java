package com.robertx22.database.stats.stat_mods.flat.elemental.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.pene.WaterPene;

public class WaterPeneFlat extends BaseElePeneFlat {

	public WaterPeneFlat() {
	}

	@Override
	public String GUID() {
		return "WaterPeneFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new WaterPene();
	}

}
