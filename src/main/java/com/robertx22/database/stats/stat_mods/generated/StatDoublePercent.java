package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.BaseTrait;
import com.robertx22.db_lists.Stats;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class StatDoublePercent extends StatMod implements IGenerated<StatMod> {

    public StatDoublePercent(Stat stat) {
        this.basestat = stat;
    }

    Stat basestat;

    @Override
    public Stat GetBaseStat() {
        return basestat;
    }

    @Override
    public float Min() {
        return 50;
    }

    @Override
    public float Max() {
        return 100;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public String GUID() {
        return basestat.GUID() + "_all_stats_double_percent";
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {

        List<StatMod> list = new ArrayList<>();

        for (Stat stat : Stats.All.values()) {
            if (stat instanceof BaseTrait == false) {
                list.add(new StatDoublePercent(stat));
            }
        }

        return list;

    }
}
