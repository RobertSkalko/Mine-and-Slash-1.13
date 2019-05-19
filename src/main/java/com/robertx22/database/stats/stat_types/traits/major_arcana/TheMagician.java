package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.IntelligenceFlat;
import com.robertx22.database.stats.stat_mods.multi.resources.ManaMulti;
import com.robertx22.database.stats.stat_mods.percent.ManaRegenPercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;

import java.util.Arrays;
import java.util.List;

public class TheMagician extends BaseMajorArcana {

    public static final String GUID = "TheMagician";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new IntelligenceFlat(), new ManaRegenPercent(), new ManaMulti(), new SpellThunderDamagePercent());
    }

}
