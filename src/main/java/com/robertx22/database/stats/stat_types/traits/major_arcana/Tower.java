package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.VitalityFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class Tower extends BaseMajorArcana {

    public static final String GUID = "Tower";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new VitalityFlat(), new MajorArmorFlat(), new FireResistFlat(), new HealthPercent());
    }

}
