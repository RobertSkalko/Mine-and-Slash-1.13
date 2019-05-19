package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.flat.misc.BonusExpFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthRegenPercent;

import java.util.Arrays;
import java.util.List;

public class TheStar extends BaseMajorArcana {

    public static final String GUID = "Star";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new HealthRegenFlat(), new HealthRegenPercent(), new ThunderResistFlat(), new BonusExpFlat());
    }

}
