package com.robertx22.database.affixes.suffixes.resource;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.ManaRegenPercent;

import java.util.Arrays;
import java.util.List;

public class OfTheSage extends Suffix {

    public OfTheSage() {
    }

    @Override
    public String GUID() {
        return "Of The Sage";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaRegenPercent(), new ManaRegenFlat());
    }

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.jewerlyOnly());
    }
}
