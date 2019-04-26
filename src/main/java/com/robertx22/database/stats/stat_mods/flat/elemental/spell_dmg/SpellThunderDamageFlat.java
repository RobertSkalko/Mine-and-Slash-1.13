package com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellThunderDamage;

public class SpellThunderDamageFlat extends BaseEleSpellDmgFlat {

	public SpellThunderDamageFlat() {
	}

	@Override
	public String GUID() {
		return "SpellThunderDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellThunderDamage();
	}

}
