package com.robertx22.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Thundering extends BaseDamagePercentPrefix {

    public Thundering() {
    }

    @Override
    public String GUID() {
        return "Thundering";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return "Thundering";
    }
}
