package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.StatModSizes;
import com.robertx22.database.stats.stat_types.core_stats.Vitality;

public class VitalityFlat extends BaseCoreStatFlat {
    public VitalityFlat(StatModSizes size) {
        super(size);
    }

    public VitalityFlat() {
        super(StatModSizes.Medium);
    }

    @Override
    public StatMod newGeneratedInstance(StatModSizes size) {
        return new VitalityFlat(size);
    }

    @Override
    public Stat GetBaseStat() {
        return new Vitality();
    }

    @Override
    public String GUID() {
        return "VitalityFlat";
    }
}
