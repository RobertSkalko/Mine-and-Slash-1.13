package com.robertx22.customitems.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.unique_items.bases.BaseUniqueRing;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.weapon_damages.BowDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.DodgePercent;

public class RingDodge extends BaseUniqueRing {

    public RingDodge() {

    }

    @Override
    public int Tier() {
	return 1;
    }

    @Override
    public String GUID() {
	return "ringdodge0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new DodgeFlat(), new DodgePercent(), new NatureResistFlat(), new HealthFlat(),
		new BowDamageFlat());
    }

}
