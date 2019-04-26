package com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackWaterDamage;

public class AttackWaterDamageFlat extends BaseEleAttackDmgFlat {

	public AttackWaterDamageFlat() {
	}

	@Override
	public String GUID() {
		return "AttackWaterDamageFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new AttackWaterDamage();
	}

}
