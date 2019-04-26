package com.robertx22.database.stats.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.conversion.thunder_to.ThunderToFireConversion;

public class ThunderToFireConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToFireConversion();

    }

    @Override
    public String GUID() {
	return "ThunderToFireConvFlat";
    }

}
