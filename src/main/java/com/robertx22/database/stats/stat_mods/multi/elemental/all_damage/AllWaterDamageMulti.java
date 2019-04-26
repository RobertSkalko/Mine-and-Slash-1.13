package com.robertx22.database.stats.stat_mods.multi.elemental.all_damage;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.BaseEleMulti;
import com.robertx22.database.stats.stat_types.elementals.all_damage.AllWaterDamage;

public class AllWaterDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new AllWaterDamage();

    }

    @Override
    public String GUID() {
	return "AllWaterDamageMulti";
    }

}
