package com.robertx22.database.stats.stat_mods.flat.elemental.resist;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.WaterResist;

public class WaterResistFlat extends BaseEleResistFlat {

	public WaterResistFlat() {
	}

	@Override
	public String GUID() {
		return "WaterResistFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new WaterResist();
	}

}
