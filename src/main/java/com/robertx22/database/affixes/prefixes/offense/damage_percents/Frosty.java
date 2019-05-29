package com.robertx22.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Frosty extends BaseDamagePercentPrefix {

    public Frosty() {
    }

    @Override
    public String GUID() {
        return "Frosty";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new SpellWaterDamagePercent());
    }

}
