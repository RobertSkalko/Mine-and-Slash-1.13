package com.robertx22.database.stats.stat_types.traits;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.defense.CriticalHitMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Lucky extends Trait implements IAffectsOtherStats {

    public static String GUID = "Lucky";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CriticalHitMulti());

    }

}
