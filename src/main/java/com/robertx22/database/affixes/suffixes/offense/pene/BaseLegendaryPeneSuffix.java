package com.robertx22.database.affixes.suffixes.offense.pene;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.LevelRequirement;
import com.robertx22.database.affixes.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;

import java.util.Arrays;
import java.util.List;

public class BaseLegendaryPeneSuffix extends Suffix {

    @Override
    public String GUID() {
        return "Of Firestorms";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new FirePeneFlat(), new FireSpellToAttackFlat());

    }

    @Override
    public int Weight() {
        return this.LegendaryWeight;
    }

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.jewerlyOnly(), new LevelRequirement(25));
    }
}
