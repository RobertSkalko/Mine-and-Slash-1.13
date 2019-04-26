package com.robertx22.database.stats.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.transfers.nature_to.NatureToThunderTransfer;

public class NatureToThunderTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToThunderTransfer();

    }

    @Override
    public String GUID() {
	return "NatureToThunderTransferFlat";
    }

}
