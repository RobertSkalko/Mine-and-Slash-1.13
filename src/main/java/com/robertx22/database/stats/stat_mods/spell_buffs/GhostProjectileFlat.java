package com.robertx22.database.stats.stat_mods.spell_buffs;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.spell_buff_traits.GhostProjectileTrait;

public class GhostProjectileFlat extends BaseSpellBuffTrait {

    public GhostProjectileFlat() {
    }

    @Override
    public String GUID() {
        return "GhostProjectileFlat";
    }

    @Override
    public Stat GetBaseStat() {
        return new GhostProjectileTrait();
    }

}
