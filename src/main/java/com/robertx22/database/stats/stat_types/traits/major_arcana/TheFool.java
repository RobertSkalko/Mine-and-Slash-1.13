package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.DexterityFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;

import java.util.Arrays;
import java.util.List;

public class TheFool extends BaseMajorArcana {

    public static final String GUID = "TheFool";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new DexterityFlat(), new CriticalHitFlat(), new HealthFlat(), new SpellThunderDamagePercent());
    }

}
