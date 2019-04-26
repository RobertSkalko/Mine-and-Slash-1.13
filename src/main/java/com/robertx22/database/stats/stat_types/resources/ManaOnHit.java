package com.robertx22.database.stats.stat_types.resources;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatEffects.ManaOnHitEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public class ManaOnHit extends Stat implements IStatEffects {
    public static String GUID = "Mana On Hit";

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new ManaOnHitEffect());
    }

    @Override
    public String unlocString() {
	return "mana_on_hit";
    }

    public ManaOnHit() {
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
	return false;
    }

}