package com.robertx22.database.map_mods.minus.all_ele_dmg;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.generated.AllElementalDamage;
import com.robertx22.uncommon.enumclasses.Elements;

public class LessAllNatureDamageMap extends BaseAllEleDmgMap {

    @Override
    public Stat GetBaseStat() {
        return new AllElementalDamage(Elements.Nature);
    }

    @Override
    public String GUID() {
        return "LessAllNatureDamageMap";
    }

}
