package com.robertx22.database.stats.stat_mods.multi.elemental.damage;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;

public class SpellNatureDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new SpellNatureDamage();

    }

    @Override
    public String GUID() {
	return "SpellNatureDamageMulti";
    }

}
