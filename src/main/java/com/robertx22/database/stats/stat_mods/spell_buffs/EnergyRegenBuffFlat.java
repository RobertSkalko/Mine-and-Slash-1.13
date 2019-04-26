package com.robertx22.database.stats.stat_mods.spell_buffs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.spell_buff_traits.BuffEnergyRegenTrait;

public class EnergyRegenBuffFlat extends BaseTraitMod {

    public EnergyRegenBuffFlat() {
    }

    @Override
    public String GUID() {
	return "EnergyRegenBuffFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new BuffEnergyRegenTrait();
    }

}
