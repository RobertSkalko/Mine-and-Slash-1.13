package com.robertx22.database.affixes.prefixes.offense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellNatureDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Thorny extends Prefix {

    public Thorny() {
    }

    @Override
    public String GUID() {
        return "Thorny";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new SpellNatureDamagePercent());
    }

}
