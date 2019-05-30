package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;

import java.util.Arrays;
import java.util.List;

public class Temperance extends BaseMajorArcana {

    public static final String GUID = "Temperance";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new HealthRegenFlat(), new EnergyRegenFlat(), new ManaRegenFlat(), new FirePeneFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Temperance";
    }
}
