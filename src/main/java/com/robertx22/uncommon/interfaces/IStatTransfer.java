package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.TransferMethod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;

import java.util.List;

public interface IStatTransfer extends IGUID {

    public abstract List<TransferMethod> Transfer();

    public default void transferStats(Unit copy, Unit unit, StatData data) {

        for (TransferMethod stat : this.Transfer()) {

            float val = copy.MyStats.get(stat.converted.GUID()).Flat * data.Value /* percent */ / 100;

            unit.MyStats.get(stat.converted.GUID()).Flat -= val;
            unit.MyStats.get(stat.statThatBenefits.GUID()).Flat += val;

        }

    }

}
