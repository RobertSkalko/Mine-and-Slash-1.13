package com.robertx22.database.unique_items.staffs;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.percent.pene.WaterPenePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueStaff;

import java.util.Arrays;
import java.util.List;

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
        return Arrays.asList(new AttackWaterDamageFlat(), new CriticalDamageFlat(), new CriticalHitFlat(), new WaterPeneFlat(), new WaterPenePercent());
    }

}
