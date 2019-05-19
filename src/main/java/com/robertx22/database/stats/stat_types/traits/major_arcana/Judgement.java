package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;

import java.util.Arrays;
import java.util.List;

public class Judgement extends BaseMajorArcana {

    public static final String GUID = "Judgement";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new WaterPeneFlat(), new FirePeneFlat(), new LifestealFlat());
    }

}
