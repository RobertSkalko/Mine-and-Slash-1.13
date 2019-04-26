package com.robertx22.database.prefixes.defense.element;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

public class PrefixWaterRes extends BaseEleResPrefix {

    @Override
    public String GUID() {
	return "Water Shield";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new WaterResistFlat(), new HealthPercent(), new ArmorFlat());

    }

}
