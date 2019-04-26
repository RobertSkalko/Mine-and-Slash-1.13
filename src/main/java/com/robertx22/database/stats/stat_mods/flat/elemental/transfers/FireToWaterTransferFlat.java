package com.robertx22.database.stats.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.transfers.fire_to.FireToWaterTransfer;

public class FireToWaterTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToWaterTransfer();
    }

    @Override
    public String GUID() {
	return "FireToWaterTransferFlat";
    }

}
