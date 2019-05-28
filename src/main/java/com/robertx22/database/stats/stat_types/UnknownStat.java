package com.robertx22.database.stats.stat_types;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class UnknownStat extends Stat {
    public static String GUID = "Renamed or Deleted Stat Error";

    public UnknownStat() {

    }

    @Override
    public String statDescription() {
        return "";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
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
