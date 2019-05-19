package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.DodgePercent;

import java.util.Arrays;
import java.util.List;

public class Hermit extends BaseMajorArcana {

    public static final String GUID = "Hermit";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new WisdomFlat(), new DodgePercent(), new CriticalHitFlat(), new SpellNatureDamageFlat());
    }

}
