package com.robertx22.database.stats.stat_types.resources;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.LifeOnHitEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class LifeOnHit extends Stat implements IStatEffects {
    public static String GUID = "Life On Hit";

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new LifeOnHitEffect());
    }

    public LifeOnHit() {
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

}
