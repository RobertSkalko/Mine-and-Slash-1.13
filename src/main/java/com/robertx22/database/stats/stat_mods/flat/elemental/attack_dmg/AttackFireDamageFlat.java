package com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackFireDamage;

public class AttackFireDamageFlat extends BaseEleAttackDmgFlat {

	public AttackFireDamageFlat() {
	}

	@Override
	public String GUID() {
		return "AttackFireDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new AttackFireDamage();
	}

}
