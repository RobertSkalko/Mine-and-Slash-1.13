package com.robertx22.database.stats.stat_types.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class PhysicalDamage extends Stat {
    public static String GUID = "Physical Damage";

    @Override
    public String statDescription() {
        return "How much DMG your basic attacks do";
    }

    public PhysicalDamage() {
        this.StatMinimum = 1;
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
