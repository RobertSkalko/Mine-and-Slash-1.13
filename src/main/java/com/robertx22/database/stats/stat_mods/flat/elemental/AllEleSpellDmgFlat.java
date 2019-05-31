package com.robertx22.database.stats.stat_mods.flat.elemental;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.elementals.all_damage.AllEleSpellDmg;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class AllEleSpellDmgFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return new AllEleSpellDmg();
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "AllEleSpellDmgFlat";
    }

}
