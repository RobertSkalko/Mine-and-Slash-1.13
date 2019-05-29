package com.robertx22.database.affixes.prefixes.offense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.LevelRequirement;
import com.robertx22.database.affixes.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.IntelligenceFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.SpellDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Arcanists extends Prefix {

    @Override
    public String GUID() {
        return "arcanists";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new SpellDamagePercent(), new IntelligenceFlat(), new ManaRegenFlat());
    }

    @Override
    public int Weight() {
        return EpicWeight;
    }

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.jewerlyOnly(), new LevelRequirement(20));
    }
}
