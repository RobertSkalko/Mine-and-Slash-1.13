package com.robertx22.database.affixes.prefixes.offense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;

import java.util.Arrays;
import java.util.List;

public class HardHitting extends Prefix {

    public HardHitting() {
    }

    @Override
    public String GUID() {
        return "Hard Hitting";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new CriticalDamageFlat());

    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.weaponsOnly());
    }
}
