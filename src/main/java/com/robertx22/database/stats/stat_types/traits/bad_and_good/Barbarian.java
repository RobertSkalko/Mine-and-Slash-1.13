package com.robertx22.database.stats.stat_types.traits.bad_and_good;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.resources.LessManaMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

public class Barbarian extends Trait implements IAffectsOtherStats {

    public static String GUID = "Barbarian";

    @Override
    public String unlocString() {
	return "barbarian";
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new PhysicalDamageMulti(), new LessManaMulti());

    }

    @Override
    public String Description() {
	return "";
    }
}