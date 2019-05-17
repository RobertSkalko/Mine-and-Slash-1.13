package com.robertx22.database.stats.stat_types.resources;

import com.robertx22.database.stats.FillableStat;
import com.robertx22.uncommon.enumclasses.Elements;

public class Mana extends FillableStat {
    public static String GUID = "Mana";

    public Mana() {

    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

}
