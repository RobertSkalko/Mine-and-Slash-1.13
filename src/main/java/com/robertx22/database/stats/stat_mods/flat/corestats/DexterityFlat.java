package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.StatModSizes;
import com.robertx22.database.stats.stat_types.core_stats.Dexterity;

public class DexterityFlat extends BaseCoreStatFlat {

    public DexterityFlat(StatModSizes size) {
        super(size);
    }

    public DexterityFlat() {
        super(StatModSizes.Medium);
    }

    @Override
    public Stat GetBaseStat() {
        return new Dexterity();
    }

    @Override
    public String GUID() {
        return "DexterityFlat";
    }

    @Override
    public StatMod newGeneratedInstance(StatModSizes size) {
        return new DexterityFlat(size);
    }
}
