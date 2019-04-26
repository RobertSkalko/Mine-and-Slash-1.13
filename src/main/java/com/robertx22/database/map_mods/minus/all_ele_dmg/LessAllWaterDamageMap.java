package com.robertx22.database.map_mods.minus.all_ele_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.all_damage.AllWaterDamage;

public class LessAllWaterDamageMap extends BaseAllEleDmgMap {

	@Override
	public Stat GetBaseStat() {
		return new AllWaterDamage();
	}

	@Override
	public String GUID() {
		return "LessAllWaterDamageMap";
	}

}
