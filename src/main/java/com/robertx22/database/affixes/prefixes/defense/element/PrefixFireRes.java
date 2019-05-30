package com.robertx22.database.affixes.prefixes.defense.element;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class PrefixFireRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
        return "Fire Shield";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new FireResistFlat(), new HealthPercent(), new ArmorFlat());

    }

    @Override
    public String locNameForLangFile() {
        return "Fire Shield";
    }
}
