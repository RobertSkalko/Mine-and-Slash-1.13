package com.robertx22.database.affixes.suffixes.offense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class OfForce extends Suffix {

    public OfForce() {
    }

    @Override
    public String GUID() {
        return "OfForce";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new CriticalDamageFlat(), new PhysicalDamagePercent());
    }

    @Override
    public int Weight() {
        return IWeighted.EpicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.weaponsOnly(), new LevelRequirement(15));
    }
}