package com.robertx22.database.stats.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.conversion.nature_to.NatureToThunderConversion;

public class NatureToThunderConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToThunderConversion();

    }

    @Override
    public String GUID() {
	return "NatureToThunderConvFlat";
    }

}
