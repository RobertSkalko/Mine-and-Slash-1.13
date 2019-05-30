package com.robertx22.database.affixes.prefixes.defense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.gearitemslots.Shield;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class Gatekeepers extends Prefix {

    @Override
    public String GUID() {
        return "gatekeepers";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new MajorArmorFlat(), new HealthPercent());
    }

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Shield()));
    }

}
