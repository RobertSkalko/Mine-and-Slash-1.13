package com.robertx22.database.stats.stat_types.traits.low_crit_hit;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.multi.defense.HealthMulti;
import com.robertx22.database.stats.stat_types.traits.bases.BaseTraitLowCritHit;

public class LowCritHitAddHealth extends BaseTraitLowCritHit {

    @Override
    public String Guid() {
	return "LowCritHitAddHealthMulti";
    }

    @Override
    public String unlocString() {
	return "health_on_low_crit";
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new HealthMulti());

    }

}
