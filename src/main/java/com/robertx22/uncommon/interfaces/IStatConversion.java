package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;

import java.util.List;

public interface IStatConversion extends IGUID {

    public abstract List<ConversionMethod> conversion();

    public default void convertStats(Unit copy, Unit unit, StatData data) {

        for (ConversionMethod stat : this.conversion()) {

            float val = copy.MyStats.get(stat.converted.GUID()).Flat * data.Value /* percent */ / 100;

            unit.MyStats.get(stat.statThatBenefits.GUID()).Flat += val;

        }

    }

}
