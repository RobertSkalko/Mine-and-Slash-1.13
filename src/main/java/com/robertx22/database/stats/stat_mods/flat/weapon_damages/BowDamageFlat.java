package com.robertx22.database.stats.stat_mods.flat.weapon_damages;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.offense.weapon_types.BowDamage;

public class BowDamageFlat extends BaseWeaponDamageFlat {

    @Override
    public Stat GetBaseStat() {
	return new BowDamage();
    }

    @Override
    public String GUID() {
	return "BowDamageFlat";
    }

}
