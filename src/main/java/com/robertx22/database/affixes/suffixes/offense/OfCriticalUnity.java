package com.robertx22.database.affixes.suffixes.offense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.LevelRequirement;
import com.robertx22.database.affixes.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;

import java.util.Arrays;
import java.util.List;

public class OfCriticalUnity extends Suffix {

    public OfCriticalUnity() {
    }

    @Override
    public String GUID() {
        return "Of Critical Unity";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new CriticalDamageFlat(), new CriticalHitFlat());
    }

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.weaponsOnly(), new LevelRequirement(10));
    }
}
