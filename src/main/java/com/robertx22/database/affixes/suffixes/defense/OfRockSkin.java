package com.robertx22.database.affixes.suffixes.defense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.ArmorPercent;

import java.util.Arrays;
import java.util.List;

public class OfRockSkin extends Suffix {

    public OfRockSkin() {
    }

    @Override
    public String GUID() {
        return "Of Rock Skin";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ArmorPercent());

    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }
}
