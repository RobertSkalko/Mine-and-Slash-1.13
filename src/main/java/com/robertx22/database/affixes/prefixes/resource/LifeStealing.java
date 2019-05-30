package com.robertx22.database.affixes.prefixes.resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.LifestealPercent;

import java.util.Arrays;
import java.util.List;

public class LifeStealing extends Prefix {

    public LifeStealing() {
    }

    @Override
    public String GUID() {
        return "Life Stealing";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new LifestealPercent());
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.weaponsOnly());
    }
}
