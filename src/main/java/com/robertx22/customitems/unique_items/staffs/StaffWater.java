package com.robertx22.customitems.unique_items.staffs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.unique_items.bases.BaseUniqueStaff;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stats.stat_mods.percent.pene.WaterPenePercent;

public class StaffWater extends BaseUniqueStaff {

    public StaffWater() {

    }

    @Override
    public int Tier() {
	return 5;
    }

    @Override
    public String GUID() {
	return "uniquestaffwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackWaterDamageFlat(), new CriticalDamageFlat(), new CriticalHitFlat(),
		new WaterPeneFlat(), new WaterPenePercent());
    }

}
