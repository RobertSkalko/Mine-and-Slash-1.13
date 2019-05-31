package com.robertx22.database.affixes.suffixes;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.misc.BonusExpFlat;

import java.util.Arrays;
import java.util.List;

public class OfGuidance extends Suffix {

    @Override
    public String GUID() {
        return "of_guidance";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new BonusExpFlat());
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Helmet()));
    }

    @Override
    public String locNameForLangFile() {
        return "Of Guidance";
    }
}
