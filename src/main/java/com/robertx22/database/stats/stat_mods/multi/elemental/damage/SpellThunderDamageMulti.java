package com.robertx22.database.stats.stat_mods.multi.elemental.damage;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellThunderDamage;

public class SpellThunderDamageMulti extends BaseEleMulti {

    @Override
    public Stat GetBaseStat() {
	return new SpellThunderDamage();

    }

    @Override
    public String GUID() {

	return "SpellThunderDamageMulti";
    }

}
