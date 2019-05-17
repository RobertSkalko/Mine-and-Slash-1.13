package com.robertx22.database.stats.stat_types.resources.conversions;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stats.stat_types.resources.EnergyRegen;
import com.robertx22.database.stats.stat_types.resources.ManaRegen;

import java.util.Arrays;
import java.util.List;

public class ManaToEnergyConversion extends BaseConversionMod {

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(new ManaRegen(), new EnergyRegen()));

    }

    @Override
    public String GUID() {
        return "ManaToEnergyConversion";
    }

}
