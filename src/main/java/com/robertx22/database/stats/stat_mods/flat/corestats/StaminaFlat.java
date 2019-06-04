package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.core_stats.Stamina;

public class StaminaFlat extends BaseCoreStatFlat {

    public StaminaFlat() {
        super();
    }

    @Override
    public Stat GetBaseStat() {
        return new Stamina();
    }

    @Override
    public String GUID() {
        return "StaminaFlat";
    }
}
