package com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;

public class SpellNatureDamageFlat extends BaseEleSpellDmgFlat {

	public SpellNatureDamageFlat() {
	}

	@Override
	public String GUID() {
		return "SpellNatureDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellNatureDamage();
	}

}
