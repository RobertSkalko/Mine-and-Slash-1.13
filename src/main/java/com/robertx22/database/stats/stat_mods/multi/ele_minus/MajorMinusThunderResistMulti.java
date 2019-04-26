package com.robertx22.database.stats.stat_mods.multi.ele_minus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.ThunderResist;

public class MajorMinusThunderResistMulti extends BaseMajorEleResistMinus {

	public MajorMinusThunderResistMulti() {
	}

	@Override
	public String GUID() {
		return "MajorMinusThunderResistMulti";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderResist();
	}

}