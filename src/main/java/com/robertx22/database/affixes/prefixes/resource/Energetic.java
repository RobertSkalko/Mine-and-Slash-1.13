package com.robertx22.database.affixes.prefixes.resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.SlotRequirement;
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

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.jewerlyOnly());
    }
}
