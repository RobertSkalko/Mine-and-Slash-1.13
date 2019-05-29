package com.robertx22.database.affixes.prefixes.misc;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;

import java.util.Arrays;
import java.util.List;

public class ThirstOfFlame extends BaseThirstPrefix {

    @Override
    public String GUID() {
        return "Thirst Of Flame";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new LifestealFlat(), new SpellFireDamagePercent());
    }

}
