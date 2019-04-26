package com.robertx22.database.stats.stat_types.elementals.attack_damage;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatEffects.offense.ElementalAttackDamageEffect;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public abstract class BaseElementalAttackDamage extends Stat implements IStatEffects {

	public BaseElementalAttackDamage() {
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ElementalAttackDamageEffect());
	}
}
