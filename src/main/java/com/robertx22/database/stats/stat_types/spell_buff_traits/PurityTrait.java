package com.robertx22.database.stats.stat_types.spell_buff_traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatEffects.spell_buffs.PurityBuff;
import com.robertx22.database.stats.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class PurityTrait extends SpellBuffTrait {

    public static String GUID = "Purity";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public String unlocString() {
	return "purity";
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new PurityBuff());
    }

}
