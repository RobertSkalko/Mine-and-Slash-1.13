package com.robertx22.database.affixes.suffixes.defense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class OfVitality extends Suffix {

    public OfVitality() {
    }

    @Override
    public String GUID() {
        return "Of Vitality";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new HealthPercent());

    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }
}
