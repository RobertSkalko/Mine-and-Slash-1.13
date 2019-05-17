package com.robertx22.database.stats.stat_types.traits.ele_lords;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllWaterDamageMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class LordOfBlizzardsTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new AllWaterDamageMulti());

    }

    @Override
    public String GUID() {
        return "Lord Of Blizzards";
    }

}
