package com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackThunderDamage;

public class AttackThunderDamageFlat extends BaseEleAttackDmgFlat {

	public AttackThunderDamageFlat() {
	}

	@Override
	public String GUID() {
		return "AttackThunderDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new AttackThunderDamage();
	}

}
