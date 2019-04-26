package com.robertx22.database.stats.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.transfers.thunder_to.ThunderToNatureTransfer;

public class ThunderToNatureTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToNatureTransfer();

    }

    @Override
    public String GUID() {
	return "ThunderToNatureTransferFlat";
    }

}
