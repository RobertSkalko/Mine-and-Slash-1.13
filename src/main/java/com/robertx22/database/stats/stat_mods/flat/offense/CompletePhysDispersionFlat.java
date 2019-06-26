package com.robertx22.database.stats.stat_mods.flat.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.offense.PhysicalDispersion;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public class CompletePhysDispersionFlat extends StatMod {

    @Override
    public String GUID() {
        return "CompletePhysDispersionFlat";
    }

    @Override
    public float Min() {
        return 100;

    }

    @Override
    public float Max() {
        return 100;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new PhysicalDispersion();
    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }
}

