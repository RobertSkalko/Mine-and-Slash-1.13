package com.robertx22.database.affixes.prefixes.resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class InnerSpirit extends Prefix {

    @Override
    public String GUID() {
        return "Inner Spirit";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new EnergyRegenFlat(), new EnergyRegenPercent());
    }

    @Override
    public int Weight() {
        return this.LegendaryWeight;
    }

}
