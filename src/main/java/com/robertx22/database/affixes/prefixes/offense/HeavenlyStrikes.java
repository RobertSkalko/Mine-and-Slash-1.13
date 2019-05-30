package com.robertx22.database.affixes.prefixes.offense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.MajorCriticalHitPercent;

import java.util.Arrays;
import java.util.List;

public class HeavenlyStrikes extends Prefix {

    @Override
    public String GUID() {
        return "Heavenly Strikes";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new CriticalHitFlat(), new MajorCriticalHitPercent());

    }

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.weaponsOnly());
    }
}
