package com.robertx22.database.affixes.suffixes.offense.pene;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.NatureSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.NaturePeneFlat;

import java.util.Arrays;
import java.util.List;

public class OfEarthquakes extends BaseLegendaryPeneSuffix {

    @Override
    public String GUID() {
        return "Of Earthquakes";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new NaturePeneFlat(), new NatureSpellToAttackFlat());

    }

    @Override
    public String locNameForLangFile() {
        return "Of Earthquakes";
    }

}
