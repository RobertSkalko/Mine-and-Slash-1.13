package com.robertx22.database.stats.stat_types.elementals.all_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AllThunderDamage extends AllEleDamageBase {
    public static String GUID = "All Thunder Damage";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

}
