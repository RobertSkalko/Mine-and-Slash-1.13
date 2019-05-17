package com.robertx22.database.stats.stat_types.elementals.resist;

import com.robertx22.uncommon.enumclasses.Elements;

public class WaterResist extends BaseEleResist {
    public static String GUID = "Water Resist";

    public WaterResist() {
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Water;
    }

}
