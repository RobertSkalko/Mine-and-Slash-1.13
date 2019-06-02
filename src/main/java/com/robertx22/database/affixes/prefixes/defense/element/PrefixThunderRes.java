package com.robertx22.database.affixes.prefixes.defense.element;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class PrefixThunderRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
        return "Thunder Shield";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalResistFlat(Elements.Thunder), new HealthPercent(), new ArmorFlat());

    }

    @Override
    public String locNameForLangFile() {
        return "Thunder Shield";
    }
}
