package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.IntelligenceFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.StrengthFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.VitalityFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;

import java.util.Arrays;
import java.util.List;

public class Chariot extends BaseMajorArcana {

    public static final String GUID = "Chariot";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new IntelligenceFlat(), new StrengthFlat(), new VitalityFlat(), new WaterSpellToAttackFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Chariot";
    }
}
