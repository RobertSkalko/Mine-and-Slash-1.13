package com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.low_dodge.LowDodgeAddArmor;

public class LowDodgeAddArmorFlat extends BaseTraitMod {

    @Override
    public String GUID() {
	return "LowDodgeAddArmorFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LowDodgeAddArmor();
    }

}
