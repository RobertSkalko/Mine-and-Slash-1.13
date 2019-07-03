package com.robertx22.database.stats.stat_mods.flat;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.defense.BlockStrength;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class BlockStrengthFlat extends StatMod {

    public BlockStrengthFlat() {
    }

    @Override
    public String GUID() {
        return "BlockStrengthFlat";
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 7.5F;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new BlockStrength();
    }

}
