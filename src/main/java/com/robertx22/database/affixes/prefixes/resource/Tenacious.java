package com.robertx22.database.affixes.prefixes.resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.StaminaFlat;

import java.util.Arrays;
import java.util.List;

public class Tenacious extends Prefix {

    @Override
    public String GUID() {
        return "tenacious";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new StaminaFlat());
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }

    @Override
    public String locNameForLangFile() {
        return "Tenacious";
    }
}
