package com.robertx22.customitems.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.unique_items.bases.BaseUniqueRing;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.MajorCriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.MajorCriticalHitPercent;

public class RingCrit extends BaseUniqueRing {

    public RingCrit() {

    }

    @Override
    public int Tier() {
	return 4;
    }

    @Override
    public String GUID() {
	return "ringcrit0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new MajorCriticalHitPercent(), new MajorCriticalDamagePercent(), new HealthFlat(),
		new EnergyRegenFlat());
    }

}
