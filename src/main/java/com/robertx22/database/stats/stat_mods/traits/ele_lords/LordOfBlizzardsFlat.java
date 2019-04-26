package com.robertx22.database.stats.stat_mods.traits.ele_lords;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.traits.ele_lords.LordOfBlizzardsTrait;

public class LordOfBlizzardsFlat extends BaseLordTrait {

    @Override
    public String GUID() {
	return "LordOfBlizzardsFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LordOfBlizzardsTrait();
    }

}