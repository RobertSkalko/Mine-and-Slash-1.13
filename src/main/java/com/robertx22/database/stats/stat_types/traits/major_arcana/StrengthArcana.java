package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.StaminaFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.StrengthFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;

import java.util.Arrays;
import java.util.List;

public class StrengthArcana extends BaseMajorArcana {

    public static final String GUID = "StrengthArcana";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new StaminaFlat(), new StrengthFlat(), new EnergyRegenFlat(), new SpellFireDamagePercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Strength";
    }

}
