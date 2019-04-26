package com.robertx22.database.stats.stat_mods.multi.elemental.all_damage;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.BaseEleMulti;
import com.robertx22.database.stats.stat_types.elementals.all_damage.AllNatureDamage;

public class AllNatureDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new AllNatureDamage();

    }

    @Override
    public String GUID() {
	return "AllNatureDamageMulti";
    }

}
