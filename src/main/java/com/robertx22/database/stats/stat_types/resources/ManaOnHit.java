package com.robertx22.database.stats.stat_types.resources;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.ManaOnHitEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ManaOnHit extends Stat implements IStatEffects {
    public static String GUID = "Mana On Hit";

    @Override
    public String statDescription() {
        return "Gives mana on basic attack";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ManaOnHitEffect());
    }

    public ManaOnHit() {
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
        return false;
    }

}