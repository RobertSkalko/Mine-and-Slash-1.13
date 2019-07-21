package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IWeighted;

public class PhysicalDamageFlat extends StatMod {

    public PhysicalDamageFlat() {
    }

    @Override
    public String GUID() {
        return "DamageFlat";
    }

    @Override
    public float Min() {
        return 4;

    }

    @Override
    public float Max() {
        return 12;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new PhysicalDamage();
    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

}
