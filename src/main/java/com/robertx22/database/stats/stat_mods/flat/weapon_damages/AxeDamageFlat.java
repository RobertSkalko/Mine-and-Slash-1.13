package com.robertx22.database.stats.stat_mods.flat.weapon_damages;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.offense.weapon_types.AxeDamage;

public class AxeDamageFlat extends BaseWeaponDamageFlat {

    @Override
    public Stat GetBaseStat() {

	return new AxeDamage();

    }

    @Override
    public String GUID() {
	return "AxeDamageFlat";
    }

}
