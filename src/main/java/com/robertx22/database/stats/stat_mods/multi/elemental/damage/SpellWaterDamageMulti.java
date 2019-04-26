package com.robertx22.database.stats.stat_mods.multi.elemental.damage;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;

public class SpellWaterDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new SpellWaterDamage();

    }

    @Override
    public String GUID() {
	return "SpellWaterDamageMulti";
    }

}
