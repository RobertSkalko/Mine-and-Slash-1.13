package com.robertx22.customitems.unique_items.axes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.unique_items.bases.BaseUniqueAxe;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.CriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.CriticalHitPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;

public class AxeThunder extends BaseUniqueAxe {
    public AxeThunder() {

    }

    @Override
    public int Tier() {
	return 11;
    }

    @Override
    public String GUID() {
	return "axethunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackThunderDamageFlat(), new CriticalHitFlat(), new CriticalHitPercent(),
		new CriticalDamagePercent(), new CrippleLifeOnHitPercent());
    }

}
