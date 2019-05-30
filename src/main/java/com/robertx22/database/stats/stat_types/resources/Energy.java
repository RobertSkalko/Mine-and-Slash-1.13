package com.robertx22.database.stats.stat_types.resources;

import com.robertx22.database.stats.FillableStat;
import com.robertx22.uncommon.enumclasses.Elements;

public class Energy extends FillableStat {
    public static String GUID = "Energy";

    @Override
    public String locDescForLangFile() {
        return "Energy is used to do basic attacks";
    }

    public Energy() {

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

    @Override
    public String locNameForLangFile() {
        return "Energy";
    }
}
