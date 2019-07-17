package com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.ArmorPenetration;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class ArmorPenePercent extends StatMod {

    public ArmorPenePercent() {
    }

    @Override
    public String GUID() {
        return "ArmorPenePercent";
    }

    @Override
    public float Min() {
        return 5;

    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return new ArmorPenetration();
    }

}