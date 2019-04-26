package com.robertx22.customitems.unique_items.pants;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.unique_items.bases.BaseUniquePants;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.transfers.FireToWaterTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stats.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;

public class PantsWater extends BaseUniquePants {

    public PantsWater() {

    }

    @Override
    public int Tier() {
	return 13;
    }

    @Override
    public String GUID() {
	return "pantswater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new MajorArmorFlat(), new ArmorPercent(), new WaterResistFlat(),
		new FireToWaterTransferFlat(), new SpellWaterDamagePercent(), new LessDodgePercent());

    }

}
