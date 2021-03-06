package com.robertx22.mine_and_slash.database.stats.stat_mods.multi.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Mana;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class ManaMulti extends StatMod {

    @Override
    public String GUID() {
	return "ManaMulti";
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
	return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
	return new Mana();
    }

}