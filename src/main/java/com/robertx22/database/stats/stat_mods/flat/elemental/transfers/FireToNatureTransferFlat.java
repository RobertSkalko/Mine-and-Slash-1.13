package com.robertx22.database.stats.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.transfers.fire_to.FireToNatureTransfer;

public class FireToNatureTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new FireToNatureTransfer();
    }

    @Override
    public String GUID() {
	return "FireToNatureTransferFlat";
    }

}
