package com.robertx22.database.stats.stat_mods.flat.elemental.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.pene.FirePene;

public class FirePeneFlat extends BaseElePeneFlat {

	public FirePeneFlat() {
	}

	@Override
	public String GUID() {
		return "FirePeneFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FirePene();
	}

}
