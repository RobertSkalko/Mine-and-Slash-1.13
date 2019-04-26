package com.robertx22.database.stats.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.transfers.fire_to.FireToThunderTransfer;

public class FireToThunderTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToThunderTransfer();
    }

    @Override
    public String GUID() {
	return "FireToThunderTransferFlat";
    }

}
