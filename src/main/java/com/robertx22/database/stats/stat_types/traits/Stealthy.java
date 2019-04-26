package com.robertx22.database.stats.stat_types.traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.defense.DodgeMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

public class Stealthy extends Trait implements IAffectsOtherStats {

    public static String GUID = "Stealthy";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new DodgeMulti());

    }

    @Override
    public String Description() {
	return "";

    }

    @Override
    public String unlocString() {
	return "stealthy";
    }
}
