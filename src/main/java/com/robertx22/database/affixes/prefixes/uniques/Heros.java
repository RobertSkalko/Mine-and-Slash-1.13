package com.robertx22.database.affixes.prefixes.uniques;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.gearitemslots.Shield;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.requirements.UniqueTierRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.BlockStrengthPercent;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class Heros extends Prefix {

    public Heros() {
    }

    @Override
    public String GUID() {
        return "heros";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new BlockStrengthPercent(), new HealthPercent());
    }

    @Override
    public int Weight() {
        return IWeighted.EpicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements((new SlotRequirement(new Shield())), new UniqueTierRequirement(7));
    }

    @Override
    public String locNameForLangFile() {
        return "Hero's";
    }
}

