package com.robertx22.database.affixes.prefixes.resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class Energetic extends Prefix {

    public Energetic() {
    }

    @Override
    public String GUID() {
        return "Energetic";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new EnergyRegenPercent());
    }

}
