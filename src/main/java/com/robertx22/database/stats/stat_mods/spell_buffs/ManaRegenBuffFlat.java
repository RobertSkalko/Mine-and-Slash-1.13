package com.robertx22.database.stats.stat_mods.spell_buffs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.spell_buff_traits.BuffManaRegenTrait;

public class ManaRegenBuffFlat extends BaseTraitMod {

    public ManaRegenBuffFlat() {
    }

    @Override
    public String GUID() {
	return "ManaRegenBuffFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new BuffManaRegenTrait();
    }

}
