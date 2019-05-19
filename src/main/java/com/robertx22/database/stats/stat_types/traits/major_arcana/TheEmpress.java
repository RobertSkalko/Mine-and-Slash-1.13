package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.misc.BonusExpFlat;
import com.robertx22.database.stats.stat_mods.percent.CriticalDamagePercent;

import java.util.Arrays;
import java.util.List;

public class TheEmpress extends BaseMajorArcana {

    public static final String GUID = "TheEmpress";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new MajorArmorFlat(), new CriticalDamagePercent(), new BonusExpFlat(), new SpellNatureDamageFlat());
    }

}
