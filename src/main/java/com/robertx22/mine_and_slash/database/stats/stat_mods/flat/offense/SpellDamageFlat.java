package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.SpellDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class SpellDamageFlat extends StatMod {

    @Override
    public String GUID() {
        return "spell_damage_flat";
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 8;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new SpellDamage();
    }

    @Override
    public int Weight() {
        return CommonWeight;
    }

}
