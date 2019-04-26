package com.robertx22.database.stats.stat_mods.multi.ele_minus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.WaterResist;

public class MajorMinusWaterResistMulti extends BaseMajorEleResistMinus {

	public MajorMinusWaterResistMulti() {
	}

	@Override
	public String GUID() {
		return "MajorMinusWaterResistMulti";
	}

	@Override
	public Stat GetBaseStat() {
		return new WaterResist();
	}

}
