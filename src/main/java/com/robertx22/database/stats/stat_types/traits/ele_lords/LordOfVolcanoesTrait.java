package com.robertx22.database.stats.stat_types.traits.ele_lords;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllFireDamageMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class LordOfVolcanoesTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new AllFireDamageMulti());

    }

    @Override
    public String Guid() {
        return "Lord Of Volcanoes";
    }

    @Override
    public String unlocString() {
        return "Lord_of_Volcanoes";
    }

}
