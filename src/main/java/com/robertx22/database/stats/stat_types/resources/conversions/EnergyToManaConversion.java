package com.robertx22.database.stats.stat_types.resources.conversions;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.resources.EnergyRegen;
import com.robertx22.database.stats.stat_types.resources.ManaRegen;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatConversion;

import java.util.Arrays;
import java.util.List;

public class EnergyToManaConversion extends Stat implements IStatConversion {

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(new EnergyRegen(), new ManaRegen()));

    }

    @Override
    public String GUID() {
        return "EnergyToManaConversion";
    }

    @Override
    public String locNameForLangFile() {
        return "Convert Energy Reg to Mana";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "Adds to 1 based on the others' amount";
    }
}
