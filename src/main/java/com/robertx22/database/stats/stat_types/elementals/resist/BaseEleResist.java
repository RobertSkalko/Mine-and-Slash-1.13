package com.robertx22.database.stats.stat_types.elementals.resist;

import com.robertx22.database.stats.UsableStat;
import com.robertx22.database.stats.stat_effects.defense.ElementalResistEffect;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public abstract class BaseEleResist extends UsableStat implements IStatEffects {
    @Override
    public String statDescription() {
        return "Stops a % damage of that element";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ElementalResistEffect());
    }

    @Override
    public float MaximumPercent() {
        return 0.75F;
    }

    @Override
    public int AverageStat() {
        return 3;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

}
