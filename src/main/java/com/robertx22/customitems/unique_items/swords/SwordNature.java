package com.robertx22.customitems.unique_items.swords;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.unique_items.bases.BaseUniqueSword;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.stat_mods.percent.LifestealPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleCriticalDamagePercent;

public class SwordNature extends BaseUniqueSword {
    public SwordNature() {

    }

    @Override
    public int Tier() {
	return 2;
    }

    @Override
    public String GUID() {
	return "swordnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackNatureDamageFlat(), new LifestealFlat(), new LifestealPercent(),
		new HealthRegenFlat(), new CrippleCriticalDamagePercent());
    }

}
