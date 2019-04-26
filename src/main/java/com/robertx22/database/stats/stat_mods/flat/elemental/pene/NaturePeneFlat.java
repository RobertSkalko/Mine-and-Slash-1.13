package com.robertx22.database.stats.stat_mods.flat.elemental.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.pene.NaturePene;

public class NaturePeneFlat extends BaseElePeneFlat {

	public NaturePeneFlat() {
	}

	@Override
	public String GUID() {
		return "NaturePeneFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new NaturePene();
	}

}
