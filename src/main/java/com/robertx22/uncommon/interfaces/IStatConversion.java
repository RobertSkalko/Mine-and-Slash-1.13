package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;

import java.util.List;

public interface IStatConversion extends IGUID {

    public abstract void convertStats(Unit unitcopy, Unit unit, StatData data);

    public abstract List<ConversionMethod> conversion();

}
