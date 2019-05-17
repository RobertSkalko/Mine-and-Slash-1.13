package com.robertx22.database.stats.stat_types.defense;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class BlockStrength extends Stat {
    public static String GUID = "BlockStrength";

    public BlockStrength() {
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
