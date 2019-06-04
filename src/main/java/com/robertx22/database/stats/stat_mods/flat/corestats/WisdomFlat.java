package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.StatModSizes;
import com.robertx22.database.stats.stat_types.core_stats.Wisdom;

public class WisdomFlat extends BaseCoreStatFlat {

    public WisdomFlat(StatModSizes size) {
        super(size);
    }

    public WisdomFlat() {
        super(StatModSizes.Medium);
    }

    @Override
    public StatMod newGeneratedInstance(StatModSizes size) {
        return new WisdomFlat(size);
    }

    @Override
    public Stat GetBaseStat() {
        return new Wisdom();
    }

    @Override
    public String GUID() {
        return "WisdomFlat";
    }
}
