package com.robertx22.database.stats;

import com.robertx22.database.IGUID;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public abstract class StatMod implements IWeighted, IGUID {

    public StatModSizes size = StatModSizes.Medium;

    public float sizeMultiplier() {
        return size.multi;
    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

    public abstract Stat GetBaseStat();

    public abstract float Min();

    public abstract float Max();

    public abstract StatTypes Type();

    public abstract String GUID();

    public float GetFloatByPercent(int percent) {

        return (Min() + (Max() - Min()) * percent / 100) * size.multi;

    }

    public StatMod bySize(StatModSizes size) {
        this.size = size;
        return this;
    }

}