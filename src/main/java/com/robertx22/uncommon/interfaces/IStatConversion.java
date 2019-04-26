package com.robertx22.uncommon.interfaces;

import java.util.List;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;

public interface IStatConversion {

    public abstract void convertStats(Unit unitcopy, Unit unit, StatData data);

    public abstract List<ConversionMethod> conversion();

}
