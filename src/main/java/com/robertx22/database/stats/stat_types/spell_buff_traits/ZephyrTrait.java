package com.robertx22.database.stats.stat_types.spell_buff_traits;

import com.robertx22.database.stats.stat_effects.spell_buffs.ZephyrBuff;
import com.robertx22.database.stats.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.uncommon.interfaces.IStatEffect;

import java.util.Arrays;
import java.util.List;

public class ZephyrTrait extends SpellBuffTrait {

    public static String GUID = "Zephyr's Speed";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ZephyrBuff());
    }

    @Override
    public String locNameForLangFile() {
        return "Zephyr";
    }
}
