package com.robertx22.database.affixes.suffixes.defense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.LevelRequirement;
import com.robertx22.database.affixes.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;

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

        return Arrays.asList(new FireResistFlat(), new WaterResistFlat(), new NatureResistFlat(), new ThunderResistFlat());

    }

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.armorsOnly(), new LevelRequirement(20));
    }

}
