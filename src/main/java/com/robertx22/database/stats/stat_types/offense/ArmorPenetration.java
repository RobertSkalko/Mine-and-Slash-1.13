package com.robertx22.database.stats.stat_types.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatEffects.offense.PenetrationEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public class ArmorPenetration extends Stat implements IStatEffects {
    public static String GUID = "Armor Penetration";

    @Override
    public String unlocString() {
	return "armor_penetration";
    }

    public ArmorPenetration() {
    }

    @Override
    public String Guid() {
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

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new PenetrationEffect());
    }

}