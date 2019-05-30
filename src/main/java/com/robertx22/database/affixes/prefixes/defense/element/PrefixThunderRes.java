package com.robertx22.database.affixes.prefixes.defense.element;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class PrefixThunderRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
        return "Thunder Shield";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ThunderResistFlat(), new HealthPercent(), new ArmorFlat());

    }

    @Override
    public String locNameForLangFile() {
        return "Thunder Shield";
    }
}
