package com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellFireDamage;

public class SpellFireDamageFlat extends BaseEleSpellDmgFlat {

	public SpellFireDamageFlat() {
	}

	@Override
	public String GUID() {
		return "SpellFireDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellFireDamage();
	}

}
