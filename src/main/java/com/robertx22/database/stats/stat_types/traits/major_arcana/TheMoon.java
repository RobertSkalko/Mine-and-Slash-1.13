package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.StaminaFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;

import java.util.Arrays;
import java.util.List;

public class TheMoon extends BaseMajorArcana {

    public static final String GUID = "Moon";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new StaminaFlat(), new WaterResistFlat(), new HealthFlat());
    }

}
