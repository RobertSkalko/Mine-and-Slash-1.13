package com.robertx22.database.stats.stat_mods.multi.ele_minus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.NatureResist;

public class MajorMinusNatureResistMulti extends BaseMajorEleResistMinus {

	public MajorMinusNatureResistMulti() {
	}

	@Override
	public String GUID() {
		return "MajorMinusNatureResistMulti";
	}

	@Override
	public Stat GetBaseStat() {
		return new NatureResist();
	}

}