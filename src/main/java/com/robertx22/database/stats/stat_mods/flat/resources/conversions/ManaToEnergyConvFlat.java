package com.robertx22.database.stats.stat_mods.flat.resources.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.resources.conversions.ManaToEnergyConversion;

public class ManaToEnergyConvFlat extends BaseResourceConversion {

    @Override
    public Stat GetBaseStat() {
	return new ManaToEnergyConversion();
    }

    @Override
    public String GUID() {
	return "ManaToEnergyConvFlat";
    }

}
