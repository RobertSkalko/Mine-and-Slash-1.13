package com.robertx22.database.affixes.prefixes.resource.rare_resource;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class InnerSpirit extends BaseRareResourcePrefix {

    @Override
    public String GUID() {
        return "Inner Spirit";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new EnergyRegenFlat(), new EnergyRegenPercent());
    }

}
