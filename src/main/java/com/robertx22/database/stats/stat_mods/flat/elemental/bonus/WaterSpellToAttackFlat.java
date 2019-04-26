package com.robertx22.database.stats.stat_mods.flat.elemental.bonus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_to_attack.WaterSpellToAttackDMG;

public class WaterSpellToAttackFlat extends BaseSpellToAttackFlat {

    public WaterSpellToAttackFlat() {

    }

    @Override
    public Stat GetBaseStat() {
	return new WaterSpellToAttackDMG();

    }

    @Override
    public String GUID() {
	return "BonusWaterDamageFlat";
    }

}
