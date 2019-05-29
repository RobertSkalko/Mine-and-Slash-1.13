package com.robertx22.database.unique_items.rings;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.MajorCriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.offense.MajorCriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueRing;

import java.util.Arrays;
import java.util.List;

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
        return Arrays.asList(new MajorCriticalHitPercent(), new MajorCriticalDamagePercent(), new HealthFlat(), new EnergyRegenFlat());
    }

}
