package com.robertx22.database.stats.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_to_attack.NatureSpellToAttackDMG;

public class NatureSpellToAttackFlat extends BaseSpellToAttackFlat {

	public NatureSpellToAttackFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new NatureSpellToAttackDMG();
	}

	@Override
	public String GUID() {
		return "BonusNatureDamageFlat";
	}

}
