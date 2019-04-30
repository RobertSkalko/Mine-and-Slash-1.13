package com.robertx22.database.affixes.suffixes.resource;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.ManaRegenPercent;

import java.util.Arrays;
import java.util.List;

public class OfManaRegen extends Suffix {

    public OfManaRegen() {
    }

    @Override
    public String GUID() {
        return "Of Mana Regen";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaRegenPercent());
    }

}