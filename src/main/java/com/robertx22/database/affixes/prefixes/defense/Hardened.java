package com.robertx22.database.affixes.prefixes.defense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.ArmorPercent;

import java.util.Arrays;
import java.util.List;

public class Hardened extends Prefix {

    public Hardened() {
    }

    @Override
    public String GUID() {
        return "Hardened";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ArmorPercent());

    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }

}
