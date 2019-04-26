package com.robertx22.database.stats.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_to_attack.FireSpellToAttackDMG;

public class FireSpellToAttackFlat extends BaseSpellToAttackFlat {

	public FireSpellToAttackFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new FireSpellToAttackDMG();
	}

	@Override
	public String GUID() {
		return "BonusFireDamageFlat";
	}

}
