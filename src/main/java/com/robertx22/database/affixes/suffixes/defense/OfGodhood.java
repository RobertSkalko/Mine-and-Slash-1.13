package com.robertx22.database.affixes.suffixes.defense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.requirements.UniqueTierRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.MajorDodgeFlat;
import com.robertx22.database.stats.stat_mods.multi.defense.HealthMulti;

import java.util.Arrays;
import java.util.List;

public class OfGodhood extends Suffix {

    @Override
    public String GUID() {
        return "of_godhood";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new HealthMulti(), new MajorDodgeFlat(), new ArmorFlat());

    }

    @Override
    public int Weight() {
        return LegendaryWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Helmet()), LevelRequirement.fromLVL50(), new UniqueTierRequirement(10));
    }

    @Override
    public String locNameForLangFile() {
        return "Of Godhood";
    }
}
