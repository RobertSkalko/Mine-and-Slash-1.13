package com.robertx22.database.stats.stat_mods.multi.ele_minus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.FireResist;

public class MajorMinusFireResistMulti extends BaseMajorEleResistMinus {

	public MajorMinusFireResistMulti() {
	}

	@Override
	public String GUID() {
		return "MajorMinusFireResistMulti";
	}

	@Override
	public Stat GetBaseStat() {
		return new FireResist();
	}

}
