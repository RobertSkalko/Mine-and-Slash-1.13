package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.StatModSizes;
import com.robertx22.database.stats.stat_types.core_stats.Strength;

public class StrengthFlat extends BaseCoreStatFlat {

    public StrengthFlat(StatModSizes size) {
        super(size);
    }

    public StrengthFlat() {
        super(StatModSizes.Medium);
    }

    @Override
    public StatMod newGeneratedInstance(StatModSizes size) {
        return new StrengthFlat(size);
    }

    @Override
    public Stat GetBaseStat() {
        return new Strength();
    }

    @Override
    public String GUID() {
        return "StrengthFlat";
    }
}
