package com.robertx22.database.stats.stat_mods.flat.elemental.resist;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.NatureResist;

public class NatureResistFlat extends BaseEleResistFlat {

	public NatureResistFlat() {
	}

	@Override
	public String GUID() {
		return "NatureResistFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new NatureResist();
	}

}
