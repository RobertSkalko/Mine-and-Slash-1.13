package com.robertx22.database.stats.stat_types.traits.ele_lords;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.generated.AllElementalDamageMulti;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class LordOfVolcanoesTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new AllElementalDamageMulti(Elements.Fire));

    }

    @Override
    public String GUID() {
        return "Lord Of Volcanoes";
    }

    @Override
    public String locNameForLangFile() {
        return "Lord of Volcanoes";
    }
}
