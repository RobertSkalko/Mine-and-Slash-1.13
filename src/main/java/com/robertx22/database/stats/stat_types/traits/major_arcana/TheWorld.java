package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.VitalityFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.percent.ArmorPercent;

import java.util.Arrays;
import java.util.List;

public class TheWorld extends BaseMajorArcana {

    public static final String GUID = "TheWorld";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new NatureResistFlat(), new VitalityFlat(), new ArmorPercent(), new WisdomFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "The World";
    }
}
