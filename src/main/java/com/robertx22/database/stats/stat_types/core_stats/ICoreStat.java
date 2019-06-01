package com.robertx22.database.stats.stat_types.core_stats;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.IStatInterface;
import com.robertx22.database.stats.StatMod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import java.util.List;

public interface ICoreStat extends IGUID, IStatInterface {

    void addToOtherStats(UnitData unit, StatData data);

    List<StatMod> statsThatBenefit();

    float amountToReach100Percent();

}