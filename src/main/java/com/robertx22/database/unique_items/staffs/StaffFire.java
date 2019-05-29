package com.robertx22.database.unique_items.staffs;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueStaff;

import java.util.Arrays;
import java.util.List;

public class StaffFire extends BaseUniqueStaff {

    public StaffFire() {

    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "uniquestafffire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackFireDamageFlat(), new CriticalDamageFlat(), new FirePeneFlat(), new LifeOnHitFlat());
    }

}
