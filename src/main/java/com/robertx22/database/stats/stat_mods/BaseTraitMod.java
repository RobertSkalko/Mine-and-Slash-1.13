package com.robertx22.database.stats.stat_mods;

import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public abstract class BaseTraitMod extends StatMod {

    public BaseTraitMod() {
    }

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
    public int Weight() {
	return IWeighted.UncommonWeight;
    }

}