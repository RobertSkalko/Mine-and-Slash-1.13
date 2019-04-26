package com.robertx22.database.stats.stat_mods.traits.conditionals.high_dodge;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.high_dodge.HighDodgeAddPhysDamage;

public class HighDodgeAddPhysDamageFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "HighDodgeAddPhysDamageFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new HighDodgeAddPhysDamage();

    }

}
