package com.robertx22.database.stats.stat_mods.flat.resources.conversions;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.resources.conversions.EnergyToManaConversion;

public class EnergyToManaConvFlat extends BaseResourceConversion {

    @Override
    public Stat GetBaseStat() {
	return new EnergyToManaConversion();
    }

    @Override
    public String GUID() {
	return "EnergyToManaConvFlat";
    }

}
