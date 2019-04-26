package com.robertx22.database.stats.stat_types.elementals.all_damage;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatEffects.offense.AllElementalDamageEffect;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public abstract class AllEleDamageBase extends Stat implements IStatEffects {

    public AllEleDamageBase() {
	this.hasMinimumAmount = false;
    }

    @Override
    public boolean ScalesToLevel() {
	return false;
    }

    @Override
    public boolean IsPercent() {
	return true;
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new AllElementalDamageEffect());
    }

}
