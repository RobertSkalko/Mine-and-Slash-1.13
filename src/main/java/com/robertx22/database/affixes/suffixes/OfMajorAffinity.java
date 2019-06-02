package com.robertx22.database.affixes.suffixes;

import com.robertx22.database.affixes.ElementalSuffix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class OfMajorAffinity extends ElementalSuffix {

    public OfMajorAffinity(Elements element) {
        super(element);
    }

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public Suffix newStatInstance(Elements element) {
        return new OfMajorAffinity(element);
    }

    @Override
    public String GUID() {
        return "of_major_affinity_" + element.name();
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ElementalAffinityFlat(element), new ElementalSpellToAttackDMGFlat(element));
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Ring()), LevelRequirement.fromLVL50());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Major " + element.name() + " Affinity";
    }
}
