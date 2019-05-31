package com.robertx22.database.affixes.prefixes.resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;

import java.util.Arrays;
import java.util.List;

public class Wise extends Prefix {

    @Override
    public String GUID() {
        return "Wise";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new WisdomFlat());
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly());
    }

    @Override
    public String locNameForLangFile() {
        return "Wise";
    }
}
