package com.robertx22.database.affixes.suffixes.defense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.AllEleResistFlat;

import java.util.Arrays;
import java.util.List;

public class OfElementResist extends Suffix {

    public OfElementResist() {
    }

    @Override
    public String GUID() {
        return "Of Element Resist";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new AllEleResistFlat());

    }

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromLVL20());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Element Resist";
    }

}
