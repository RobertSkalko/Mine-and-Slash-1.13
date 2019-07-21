package com.robertx22.mine_and_slash.database.stats.stat_mods.flat;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.ArmorPenetration;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

public class ArmorPeneFlat extends StatMod {

    public ArmorPeneFlat() {
    }

    @Override
    public String GUID() {
        return "Armor Penetration";
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 10;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new ArmorPenetration();
    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

}
