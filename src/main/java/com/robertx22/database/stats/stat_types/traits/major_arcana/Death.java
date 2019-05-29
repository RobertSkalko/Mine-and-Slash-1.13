package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.map_mods.minus.LessHealthMap;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllWaterDamageMulti;
import com.robertx22.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Death extends BaseMajorArcana {

    public static final String GUID = "Death";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new LessHealthMap(), new AllWaterDamageMulti(), new PhysicalDamagePercent(), new LifeOnHitFlat());
    }

}
