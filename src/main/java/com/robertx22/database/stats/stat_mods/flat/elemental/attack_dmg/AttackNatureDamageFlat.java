package com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackNatureDamage;

public class AttackNatureDamageFlat extends BaseEleAttackDmgFlat {

	public AttackNatureDamageFlat() {
	}

	@Override
	public String GUID() {
		return "AttackNatureDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new AttackNatureDamage();
	}

}
