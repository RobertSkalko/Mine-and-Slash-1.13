package com.robertx22.database.stats.stat_mods.spell_buffs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.spell_buff_traits.PurityTrait;

public class PurityFlat extends BaseTraitMod {

    public PurityFlat() {
    }

    @Override
    public String GUID() {
	return "PurityFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new PurityTrait();
    }

}
