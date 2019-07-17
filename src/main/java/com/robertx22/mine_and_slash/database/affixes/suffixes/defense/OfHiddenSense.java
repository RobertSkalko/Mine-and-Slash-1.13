package com.robertx22.mine_and_slash.database.affixes.suffixes.defense;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.DodgePercent;

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
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Hidden Sense";
    }
}