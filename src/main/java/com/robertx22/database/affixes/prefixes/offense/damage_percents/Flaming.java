package com.robertx22.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Flaming extends BaseDamagePercentPrefix {

    public Flaming() {
    }

    @Override
    public String GUID() {
        return "Flaming";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new SpellFireDamagePercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Flaming";
    }

}
