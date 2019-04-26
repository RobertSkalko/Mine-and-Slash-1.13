package com.robertx22.database.stats.stat_mods.flat.elemental.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.pene.ThunderPene;

public class ThunderPeneFlat extends BaseElePeneFlat {

	public ThunderPeneFlat() {
	}

	@Override
	public String GUID() {
		return "ThunderPeneFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderPene();
	}

}
