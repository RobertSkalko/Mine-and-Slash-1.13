package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.TransferMethod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;

import java.util.List;

public interface IStatTransfer extends IGUID {

    public abstract void transferStats(Unit unitcopy, Unit unit, StatData data);

    public abstract List<TransferMethod> Transfer();

}
