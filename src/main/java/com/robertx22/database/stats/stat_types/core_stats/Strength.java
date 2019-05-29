package com.robertx22.database.stats.stat_types.core_stats;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.database.stats.stat_mods.percent.PhysicalDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Strength extends BaseCoreStat {
    @Override
    public String statDescription() {
        return "Increases Physical DMG, Critical DMG, Physical DMG multi";
    }

    @Override
    public String GUID() {
        return "Strength";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new PhysicalDamagePercent(), new CriticalDamageFlat(), new PhysicalDamageMulti());
    }
}
