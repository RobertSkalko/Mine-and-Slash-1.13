package com.robertx22.database.stats;

import com.robertx22.database.IGUID;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public abstract class StatMod implements IWeighted, IGUID {

    public float multiplier = 1F;

    public float sizeMultiplier() {
        return multiplier;
    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

    public abstract Stat GetBaseStat();

    public abstract float Min();

    public abstract float Max();

    public abstract StatTypes Type();

    @Override
    public String GUID() {
        return this.GetBaseStat().GUID() + "_" + this.Type().name();
    }

    public float GetFloatByPercent(int percent) {
        return (Min() + (Max() - Min()) * percent / 100) * multiplier;
    }

    public StatMod multi(float multiplier) {
        this.multiplier = multiplier;
        return this;
    }

}