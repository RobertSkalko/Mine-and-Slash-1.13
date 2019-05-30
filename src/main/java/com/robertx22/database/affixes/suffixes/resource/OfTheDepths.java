package com.robertx22.database.affixes.suffixes.resource;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class OfTheDepths extends Suffix {

    public OfTheDepths() {
    }

    @Override
    public String GUID() {
        return "OfTheDepths";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaFlat(), new EnergyFlat(), new HealthFlat());
    }

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Necklace()));
    }

    @Override
    public String locNameForLangFile() {
        return "Of The Depths";
    }
}