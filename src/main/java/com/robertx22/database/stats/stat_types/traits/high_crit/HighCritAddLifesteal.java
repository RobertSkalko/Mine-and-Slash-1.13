package com.robertx22.database.stats.stat_types.traits.high_crit;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.stat_types.traits.bases.BaseTraitHighCritHit;

public class HighCritAddLifesteal extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new LifestealFlat());

    }

    @Override
    public String Guid() {
	return "HighCritAddLifesteal";
    }

    @Override
    public String unlocString() {
	return "lifesteal_on_high_crit";
    }

}
