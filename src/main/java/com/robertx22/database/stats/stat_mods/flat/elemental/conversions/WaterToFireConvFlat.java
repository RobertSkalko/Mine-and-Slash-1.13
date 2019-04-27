package com.robertx22.database.stats.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.conversion.water_to.WaterToFireConversion;

public class WaterToFireConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new WaterToFireConversion();
    }

    @Override
    public String GUID() {
	return "WaterToFireConvFlat";
    }

}