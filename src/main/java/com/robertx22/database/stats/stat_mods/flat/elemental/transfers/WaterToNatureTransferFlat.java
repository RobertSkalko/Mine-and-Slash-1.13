package com.robertx22.database.stats.stat_mods.flat.elemental.transfers;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.transfers.water_to.WaterToNatureTransfer;

public class WaterToNatureTransferFlat extends BaseTransferFlat {

    @Override
    public Stat GetBaseStat() {
	return new WaterToNatureTransfer();

    }

    @Override
    public String GUID() {
	return "WaterToNatureTransferFlat";
    }

}
