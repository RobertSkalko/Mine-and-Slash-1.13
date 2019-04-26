package com.robertx22.database.stats.stat_types.resources;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatEffects.LifestealEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public class Lifesteal extends Stat implements IStatEffects {
    public static String GUID = "Lifesteal";

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new LifestealEffect());
    }

    @Override
    public String unlocString() {
	return "lifesteal";
    }

    public Lifesteal() {
    }

    @Override
    public String Guid() {
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
