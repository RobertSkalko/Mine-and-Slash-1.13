package com.robertx22.database.stats.stat_mods.traits.ele_lords;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.traits.ele_lords.LordOfEarthquakesTrait;

public class LordOfEarthquakesFlat extends BaseLordTrait {

    @Override
    public String GUID() {
	return "LordOfEarthQuakesFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LordOfEarthquakesTrait();
    }

}
