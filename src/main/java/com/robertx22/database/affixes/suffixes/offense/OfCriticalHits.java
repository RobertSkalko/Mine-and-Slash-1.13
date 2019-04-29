package com.robertx22.database.affixes.suffixes.offense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;

import java.util.Arrays;
import java.util.List;

public class OfCriticalHits extends Suffix {

    public OfCriticalHits() {
    }

    @Override
    public String GUID() {
        return "Of Critical Hits";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new CriticalHitFlat());

    }

}
