package com.robertx22.database.stats.stat_types.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatEffects.defense.DodgeEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public class Dodge extends Stat implements IStatEffects {
    public static String GUID = "Dodge";

    @Override
    public String unlocString() {
	return "dodge";
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new DodgeEffect());
    }

    public Dodge() {
	MaximumPercent = 75;
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
