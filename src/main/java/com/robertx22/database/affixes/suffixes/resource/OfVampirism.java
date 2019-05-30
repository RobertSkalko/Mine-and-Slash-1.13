package com.robertx22.database.affixes.suffixes.resource;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.stat_mods.percent.LifestealPercent;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class OfVampirism extends Suffix {

    public OfVampirism() {
    }

    @Override
    public String GUID() {
        return "OfVampirism";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new LifestealFlat(), new LifeOnHitFlat(), new LifestealPercent());
    }

    @Override
    public int Weight() {
        return IWeighted.EpicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.weaponsOnly());
    }
}
