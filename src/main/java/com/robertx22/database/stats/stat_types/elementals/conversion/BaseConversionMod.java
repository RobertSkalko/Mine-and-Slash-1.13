package com.robertx22.database.stats.stat_types.elementals.conversion;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatConversion;

public abstract class BaseConversionMod extends Stat implements IStatConversion {

    public Elements fromElement;
    public Elements toElement;

    @Override
    public String locDescForLangFile() {
        return "Conversion adds a % of one to the other";
    }

    public BaseConversionMod() {
        this.MaximumPercent = 100;
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
    public String locNameForLangFile() {
        return fromElement.name() + " to " + toElement.name() + " Convert";
    }

    @Override
    public void convertStats(Unit copy, Unit unit, StatData data) {

        for (ConversionMethod stat : this.conversion()) {

            float val = copy.MyStats.get(stat.converted.GUID()).Flat * data.Value /* percent */ / 100;

            unit.MyStats.get(stat.statThatBenefits.GUID()).Flat += val;

        }

    }

}
