package com.robertx22.database.stats.stat_types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderResist extends BaseEleResist {
    public static String GUID = "Thunder Resist";

    public ThunderResist() {
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

}
