package com.robertx22.database.stats.stat_mods.spell_buffs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.spell_buff_traits.ZephyrTrait;

public class ZephyrFlat extends BaseSpellBuffTrait {

    public ZephyrFlat() {
    }

    @Override
    public String GUID() {
        return "ZephyrFlat";
    }

    @Override
    public Stat GetBaseStat() {
        return new ZephyrTrait();
    }

}
