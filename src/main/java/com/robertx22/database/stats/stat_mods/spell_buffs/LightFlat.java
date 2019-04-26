package com.robertx22.database.stats.stat_mods.spell_buffs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.spell_buff_traits.LightTrait;

public class LightFlat extends BaseTraitMod {

    public LightFlat() {
    }

    @Override
    public String GUID() {
	return "LightFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LightTrait();
    }

}
