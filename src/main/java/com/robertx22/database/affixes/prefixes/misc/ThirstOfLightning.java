package com.robertx22.database.affixes.prefixes.misc;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ThirstOfLightning extends BaseThirstPrefix {

    @Override
    public String GUID() {
        return "Thirst Of Lightning";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new LifestealFlat(), new ElementalSpellDamagePercent(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return "Thirst of Lightning";
    }
}
