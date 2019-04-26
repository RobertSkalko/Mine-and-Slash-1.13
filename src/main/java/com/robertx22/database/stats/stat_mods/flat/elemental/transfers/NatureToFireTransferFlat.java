package com.robertx22.database.stats.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.transfers.nature_to.NatureToFireTransfer;

public class NatureToFireTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new NatureToFireTransfer();
    }

    @Override
    public String GUID() {
	return "NatureToFireTransferFlat";
    }

}
