package com.robertx22.database.stats.stat_types.resources;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.LifestealEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class Lifesteal extends Stat implements IStatEffects {
    public static String GUID = "Lifesteal";

    @Override
    public String locDescForLangFile() {
        return "% of basic attack DMG added to health";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new LifestealEffect());
    }

    public Lifesteal() {
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
