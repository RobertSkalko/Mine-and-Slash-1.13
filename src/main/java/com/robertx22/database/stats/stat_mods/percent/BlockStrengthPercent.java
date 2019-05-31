package com.robertx22.database.stats.stat_mods.percent;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.defense.BlockStrength;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class BlockStrengthPercent extends StatMod {

    @Override
    public String GUID() {
        return "BlockStrengthPercent";
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 30;

    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return new BlockStrength();
    }

}