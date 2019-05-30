package com.robertx22.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Tough extends BaseDamagePercentPrefix {

    public Tough() {
    }

    @Override
    public String GUID() {
        return "Tough";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new PhysicalDamagePercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Tough";
    }
}
