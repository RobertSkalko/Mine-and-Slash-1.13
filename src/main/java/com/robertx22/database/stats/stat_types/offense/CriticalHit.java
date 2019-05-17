package com.robertx22.database.stats.stat_types.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.CriticalHitEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class CriticalHit extends Stat implements IStatEffects {
    public static String GUID = "Critical Hit";

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new CriticalHitEffect());
    }

    public CriticalHit() {
        this.BaseFlat = 1;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

}
