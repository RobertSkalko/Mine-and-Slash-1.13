package com.robertx22.database.stats;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatEffects.offense.WeaponDamageEffect;
import com.robertx22.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public abstract class WeaponDamageStat extends Stat implements IStatEffects {

    public abstract WeaponTypes weaponType();

    public WeaponDamageStat() {
	this.hasMinimumAmount = false;
    }

    @Override
    public boolean IsPercent() {
	return true;
    }

    @Override
    public boolean ScalesToLevel() {
	return false;
    }

    @Override
    public Elements Element() {
	return null;
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new WeaponDamageEffect());
    }

}
