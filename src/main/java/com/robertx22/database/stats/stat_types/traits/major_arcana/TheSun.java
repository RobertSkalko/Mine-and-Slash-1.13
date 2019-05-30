package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;

import java.util.Arrays;
import java.util.List;

public class TheSun extends BaseMajorArcana {

    public static final String GUID = "Sun";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new HealthFlat(), new EnergyFlat(), new SpellFireDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "The Sun";
    }
}
