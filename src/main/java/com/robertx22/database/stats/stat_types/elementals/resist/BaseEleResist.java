package com.robertx22.database.stats.stat_types.elementals.resist;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.UsableStat;
import com.robertx22.database.stats.StatEffects.defense.ElementalResistEffect;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public abstract class BaseEleResist extends UsableStat implements IStatEffects {

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