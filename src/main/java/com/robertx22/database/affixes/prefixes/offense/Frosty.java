package com.robertx22.database.affixes.prefixes.offense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Frosty extends Prefix {

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
