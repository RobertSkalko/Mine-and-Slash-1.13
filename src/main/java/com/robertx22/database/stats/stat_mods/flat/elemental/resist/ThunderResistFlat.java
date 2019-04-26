package com.robertx22.database.stats.stat_mods.flat.elemental.resist;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.ThunderResist;

public class ThunderResistFlat extends BaseEleResistFlat {

	public ThunderResistFlat() {
	}

	@Override
	public String GUID() {
		return "ThunderResistFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderResist();
	}

}
