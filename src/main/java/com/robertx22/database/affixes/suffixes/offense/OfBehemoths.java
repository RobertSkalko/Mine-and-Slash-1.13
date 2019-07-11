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

public class OfBehemoths extends Suffix {

    public OfBehemoths() {
    }

    @Override
    public String GUID() {
        return "of_behemoths";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new CriticalDamageFlat().multi(2), new HealthPercent());
    }

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromLVL50());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Behemoths";
    }

}