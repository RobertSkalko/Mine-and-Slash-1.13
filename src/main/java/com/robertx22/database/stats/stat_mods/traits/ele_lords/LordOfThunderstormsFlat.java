package com.robertx22.database.stats.stat_mods.traits.ele_lords;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.traits.ele_lords.LordOfThunderstormsTrait;

public class LordOfThunderstormsFlat extends BaseLordTrait {

    @Override
    public String GUID() {
	return "LordOfThunderstormsTrait";
    }

    @Override
    public Stat GetBaseStat() {
	return new LordOfThunderstormsTrait();
    }

}
