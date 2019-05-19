package com.robertx22.database.stats.stat_mods.spell_buffs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.spell_buff_traits.HomingTrait;

public class HomingFlat extends BaseSpellBuffTrait {

    public HomingFlat() {
    }

    @Override
    public String GUID() {
        return "HomingFlat";
    }

    @Override
    public Stat GetBaseStat() {
        return new HomingTrait();
    }

}
