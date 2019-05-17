package com.robertx22.database.stats.stat_types.elementals.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.TransferMethod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatTransfer;

public abstract class BaseTransferMod extends Stat implements IStatTransfer {

    public BaseTransferMod() {
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
    public void transferStats(Unit copy, Unit unit, StatData data) {

	for (TransferMethod stat : this.Transfer()) {

	    float val = copy.MyStats.get(stat.converted.GUID()).Flat * data.Value /* percent */ / 100;

	    unit.MyStats.get(stat.converted.GUID()).Flat -= val;
	    unit.MyStats.get(stat.statThatBenefits.GUID()).Flat += val;

	}

    }

}
