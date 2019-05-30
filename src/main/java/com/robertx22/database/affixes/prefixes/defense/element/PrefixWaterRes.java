package com.robertx22.database.affixes.prefixes.defense.element;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class PrefixWaterRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
        return "Water Shield";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new WaterResistFlat(), new HealthPercent(), new ArmorFlat());

    }

    @Override
    public String locNameForLangFile() {
        return "Water Shield";
    }
}