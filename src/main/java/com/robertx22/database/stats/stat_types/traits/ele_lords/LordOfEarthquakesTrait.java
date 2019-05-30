package com.robertx22.database.stats.stat_types.traits.ele_lords;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllNatureDamageMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class LordOfEarthquakesTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new AllNatureDamageMulti());

    }

    @Override
    public String GUID() {
        return "Lord Of Earthquakes";
    }

    @Override
    public String locNameForLangFile() {
        return "Lord of Earthquakes";
    }
}
