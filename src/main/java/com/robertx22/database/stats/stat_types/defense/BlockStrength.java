package com.robertx22.database.stats.stat_types.defense;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class BlockStrength extends Stat {

    @Override
    public String locDescForLangFile() {
        return "Blocks part of DMG when blocking, if all damage is blocked, attack is canceled";
    }

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

    @Override
    public String locNameForLangFile() {
        return "Block Strength";
    }
}
