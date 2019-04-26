package com.robertx22.database.stats.stat_mods.flat.weapon_damages;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.offense.weapon_types.StaffDamage;

public class StaffDamageFlat extends BaseWeaponDamageFlat {

    @Override
    public Stat GetBaseStat() {
	return new StaffDamage();

    }

    @Override
    public String GUID() {
	return "StaffDamageFlat";
    }

}
