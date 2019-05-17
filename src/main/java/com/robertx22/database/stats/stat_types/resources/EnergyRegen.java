package com.robertx22.database.stats.stat_types.resources;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class EnergyRegen extends Stat {
    public static String GUID = "Energy Regen";

    public EnergyRegen() {

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
