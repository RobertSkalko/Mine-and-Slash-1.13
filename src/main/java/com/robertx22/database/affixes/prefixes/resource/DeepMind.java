package com.robertx22.database.affixes.prefixes.resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.ManaRegenPercent;

import java.util.Arrays;
import java.util.List;

public class DeepMind extends Prefix {

    @Override
    public String GUID() {
        return "Deep Mind";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaRegenFlat(), new ManaRegenPercent());
    }

    @Override
    public int Weight() {
        return this.LegendaryWeight;
    }

}