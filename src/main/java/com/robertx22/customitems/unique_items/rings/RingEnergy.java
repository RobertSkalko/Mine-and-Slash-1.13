package com.robertx22.customitems.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.unique_items.bases.BaseUniqueRing;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleCriticalDamagePercent;

public class RingEnergy extends BaseUniqueRing {

    public RingEnergy() {

    }

    @Override
    public int Tier() {
	return 15;
    }

    @Override
    public String GUID() {
	return "ringenergy0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new EnergyRegenFlat(), new EnergyRegenPercent(), new ManaRegenFlat(), new HealthFlat(),
		new CrippleCriticalDamagePercent());
    }

}
