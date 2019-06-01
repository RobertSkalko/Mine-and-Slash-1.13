package com.robertx22.database.stats.stat_types.core_stats;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.StatMod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.uncommon.capability.EntityData;

import java.util.List;

public interface IPreCoreStat extends IGUID {

    void addToCoreStats(EntityData.UnitData unit, StatData data);

    List<StatMod> coreStatsThatBenefit();

}
