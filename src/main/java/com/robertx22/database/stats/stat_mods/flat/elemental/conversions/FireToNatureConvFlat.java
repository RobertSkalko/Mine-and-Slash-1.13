package com.robertx22.database.stats.stat_mods.flat.elemental.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.conversion.fire_to.FireToNatureConversion;

public class FireToNatureConvFlat extends BaseConversionFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToNatureConversion();
    }

    @Override
    public String GUID() {
	return "FireToNatureConvFlat";
    }

}
