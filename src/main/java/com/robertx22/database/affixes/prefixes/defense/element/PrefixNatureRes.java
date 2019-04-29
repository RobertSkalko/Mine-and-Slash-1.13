package com.robertx22.database.affixes.prefixes.defense.element;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class PrefixNatureRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
        return "Nature Shield";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new NatureResistFlat(), new HealthPercent(), new ArmorFlat());

    }

}
