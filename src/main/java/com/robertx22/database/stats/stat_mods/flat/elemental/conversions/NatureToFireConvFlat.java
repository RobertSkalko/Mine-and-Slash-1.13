package com.robertx22.database.stats.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.conversion.nature_to.NatureToFireConversion;

public class NatureToFireConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToFireConversion();
    }

    @Override
    public String GUID() {
	return "NatureToFireConvFlat";
    }

}
