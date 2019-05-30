package com.robertx22.database.affixes.prefixes.resource.rare_resource;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthRegenPercent;

import java.util.Arrays;
import java.util.List;

public class BraveHeart extends BaseRareResourcePrefix {

    @Override
    public String GUID() {
        return "Brave Heart";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new HealthRegenFlat(), new HealthRegenPercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Brave Heart";
    }
}