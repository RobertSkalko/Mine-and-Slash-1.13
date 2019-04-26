package com.robertx22.database.stats.stat_mods.multi.elemental.all_damage;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.BaseEleMulti;
import com.robertx22.database.stats.stat_types.elementals.all_damage.AllThunderDamage;

public class AllThunderDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new AllThunderDamage();

    }

    @Override
    public String GUID() {
	return "AllThunderDamageMulti";
    }

}
