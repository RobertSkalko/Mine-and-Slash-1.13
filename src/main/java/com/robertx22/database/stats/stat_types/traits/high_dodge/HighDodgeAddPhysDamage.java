package com.robertx22.database.stats.stat_types.traits.high_dodge;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.PhysicalDamagePercent;
import com.robertx22.database.stats.stat_types.traits.bases.BaseTraitHighCritHit;

public class HighDodgeAddPhysDamage extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new PhysicalDamagePercent());

    }

    @Override
    public String Guid() {
	return "HighDodgeAddPhysDamage";
    }

    @Override
    public String unlocString() {
	return "phys_damage_on_high_dodge";
    }

}
