package com.robertx22.database.stats.stat_types.core_stats;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;

public abstract class BaseCoreStat extends Stat implements ICoreStat {

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public float amountToReach100Percent() {
        return 10;
    }

    @Override
    public void addToOtherStats(UnitData unitdata, StatData data) {

        float percent = data.Value / (this.amountToReach100Percent() * unitdata.getLevel()) * 100;

        for (StatMod statmod : this.statsThatBenefit()) {
            StatModData.Load(statmod, (int) percent).useOnPlayer(unitdata);
        }

    }

}


