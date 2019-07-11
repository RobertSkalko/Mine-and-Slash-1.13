package com.robertx22.database.affixes.suffixes.offense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class OfGiants extends Suffix {

    public OfGiants() {
    }

    @Override
    public String GUID() {
        return "of_giants";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new CriticalDamageFlat(), new HealthPercent());

    }

    @Override
    public int Weight() {
        return IWeighted.RareWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromLVL10());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Giants";
    }

}

