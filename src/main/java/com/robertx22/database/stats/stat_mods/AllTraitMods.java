package com.robertx22.database.stats.stat_mods;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.BaseTrait;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class AllTraitMods extends StatMod {

    public AllTraitMods(BaseTrait stat) {
        this.GUID = stat.GUID() + "Flat";
        this.baseStat = stat;
        this.weight = stat.Weight();
    }

    private String GUID;
    private Stat baseStat;
    private int weight;

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 1;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Stat GetBaseStat() {
        return baseStat;
    }

    @Override
    public int Weight() {
        return weight;
    }
}
