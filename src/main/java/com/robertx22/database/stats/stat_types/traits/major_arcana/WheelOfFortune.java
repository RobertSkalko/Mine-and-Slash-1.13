package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.stat_mods.multi.defense.CriticalHitMulti;

import java.util.Arrays;
import java.util.List;

public class WheelOfFortune extends BaseMajorArcana {

    public static final String GUID = "WheelOfFortune";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CriticalHitFlat(), new CriticalDamageFlat(), new CriticalHitMulti(), new FirePeneFlat());
    }

}