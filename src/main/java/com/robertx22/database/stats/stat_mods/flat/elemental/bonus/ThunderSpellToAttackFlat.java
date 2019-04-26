package com.robertx22.database.stats.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_to_attack.ThunderSpellToAttackDMG;

public class ThunderSpellToAttackFlat extends BaseSpellToAttackFlat {

	public ThunderSpellToAttackFlat() {

	}

	@Override
	public Stat GetBaseStat() {
		return new ThunderSpellToAttackDMG();
	}

	@Override
	public String GUID() {
		return "BonusThunderDamageFlat";
	}

}
