package com.robertx22.database.stats.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.transfers.thunder_to.ThunderToFireTransfer;

public class ThunderToFireTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new ThunderToFireTransfer();

    }

    @Override
    public String GUID() {
	return "ThunderToFireTransferFlat";
    }

}
