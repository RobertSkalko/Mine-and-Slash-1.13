package com.robertx22.database.affixes.suffixes.defense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stats.stat_mods.percent.DodgePercent;

import java.util.Arrays;
import java.util.List;

public class OfHiddenSense extends Suffix {

    public OfHiddenSense() {
    }

    @Override
    public String GUID() {
        return "OfHiddenSense";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new DodgeFlat(), new DodgePercent());

    }

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.armorsOnly());
    }
}