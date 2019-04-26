package com.robertx22.database.stats.stat_mods.flat.elemental.resist;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.FireResist;

public class FireResistFlat extends BaseEleResistFlat {

	public FireResistFlat() {
	}

	@Override
	public String GUID() {
		return "FireResistFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new FireResist();
	}

}
