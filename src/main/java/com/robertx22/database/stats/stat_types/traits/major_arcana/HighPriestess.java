package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.IntelligenceFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthRegenPercent;

import java.util.Arrays;
import java.util.List;

public class HighPriestess extends BaseMajorArcana {

    public static final String GUID = "HighPriestess";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new WisdomFlat(), new HealthRegenPercent(), new IntelligenceFlat(), new WaterSpellToAttackFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "High Priestess";
    }
}
