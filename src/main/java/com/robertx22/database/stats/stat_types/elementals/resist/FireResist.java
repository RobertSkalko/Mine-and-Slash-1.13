package com.robertx22.database.stats.stat_types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class FireResist extends BaseEleResist {
    public static String GUID = "Fire Resist";

    public FireResist() {
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Fire;
    }

}
