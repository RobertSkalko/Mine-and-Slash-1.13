package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;

import java.util.Arrays;
import java.util.List;

public class TheDevil extends BaseMajorArcana {

    public static final String GUID = "Devil";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new HealthRegenFlat(), new EnergyRegenFlat(), new ManaRegenFlat(), new SpellNatureDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "The Devil";
    }

}
