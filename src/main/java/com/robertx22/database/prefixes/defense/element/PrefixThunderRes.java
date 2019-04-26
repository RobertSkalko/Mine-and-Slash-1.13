package com.robertx22.database.prefixes.defense.element;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

public class PrefixThunderRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
	return "Thunder Shield";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new ThunderResistFlat(), new HealthPercent(), new ArmorFlat());

    }

}
