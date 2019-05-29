package com.robertx22.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellNatureDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Thorny extends BaseDamagePercentPrefix {

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
