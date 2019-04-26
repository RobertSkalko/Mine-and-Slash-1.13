package com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;

public class SpellWaterDamageFlat extends BaseEleSpellDmgFlat {

	public SpellWaterDamageFlat() {
	}

	@Override
	public String GUID() {
		return "SpellWaterDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellWaterDamage();
	}

}
