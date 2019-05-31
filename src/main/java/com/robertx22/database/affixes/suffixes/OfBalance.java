package com.robertx22.database.affixes.suffixes;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;

import java.util.Arrays;
import java.util.List;

public class OfBalance extends Suffix {

    @Override
    public String GUID() {
        return "of_balance";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaFlat(), new EnergyFlat());
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL20());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Balance";
    }
}
