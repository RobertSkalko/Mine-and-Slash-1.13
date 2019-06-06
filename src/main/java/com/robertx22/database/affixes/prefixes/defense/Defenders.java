package com.robertx22.database.affixes.prefixes.defense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.gearitemslots.Shield;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.BlockStrengthPercent;

import java.util.Arrays;
import java.util.List;

public class Defenders extends Prefix {

    public Defenders() {
    }

    @Override
    public String GUID() {
        return "Evasive";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new BlockStrengthPercent());
    }

    @Override
    public Requirements requirements() {
        return new Requirements((new SlotRequirement(new Shield())));
    }

    @Override
    public String locNameForLangFile() {
        return "Defender's";
    }
}

