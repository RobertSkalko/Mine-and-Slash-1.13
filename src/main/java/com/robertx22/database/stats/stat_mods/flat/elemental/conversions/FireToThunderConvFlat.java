package com.robertx22.database.stats.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.conversion.fire_to.FireToThunderConversion;

public class FireToThunderConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToThunderConversion();
    }

    @Override
    public String GUID() {
	return "FireToThunderConvFlat";
    }

}
