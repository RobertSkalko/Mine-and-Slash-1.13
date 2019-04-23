package com.robertx22.database.stat_mods.percent.spell_ele_dmg;

import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class SpellNatureDamagePercent extends StatMod {

	public SpellNatureDamagePercent() {
	}

	@Override
	public String GUID() {
		return "NatureDamagePercent";
	}

	@Override
	public float Min() {
		return 2;
	}

	@Override
	public float Max() {
		return 10;
	}

	@Override
	public StatTypes Type() {
		return StatTypes.Percent;
	}

	@Override
	public Stat GetBaseStat() {
		return new SpellNatureDamage();
	}

}
