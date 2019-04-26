package com.robertx22.database.stats.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.conversion.nature_to.NatureToWaterConversion;

public class NatureToWaterConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToWaterConversion();
    }

    @Override
    public String GUID() {
	return "NatureToWaterConvFlat";
    }

}
