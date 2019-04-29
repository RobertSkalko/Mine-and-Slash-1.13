package com.robertx22.database.affixes.prefixes.defense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.DodgeFlat;

import java.util.Arrays;
import java.util.List;

public class Evasive extends Prefix {

    public Evasive() {
    }

    @Override
    public String GUID() {
        return "Evasive";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new DodgeFlat());
    }

}
