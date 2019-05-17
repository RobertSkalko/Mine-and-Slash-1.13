package com.robertx22.database.stats.stat_types.elementals.all_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AllFireDamage extends AllEleDamageBase {
    public static String GUID = "All Fire Damage";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Fire;
    }

}
