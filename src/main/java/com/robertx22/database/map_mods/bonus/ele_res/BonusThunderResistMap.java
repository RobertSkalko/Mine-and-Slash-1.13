package com.robertx22.database.map_mods.bonus.ele_res;

import com.robertx22.database.map_mods.bases.BonusEleResistBase;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.resist.ThunderResist;

public class BonusThunderResistMap extends BonusEleResistBase {

	@Override
	public String GUID() {
		return "BonusThunderResistMap";
	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderResist();

	}

}
