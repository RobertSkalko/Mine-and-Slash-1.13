package com.robertx22.database.stats.stat_types.resources;

public class EnergyRegen extends BaseRegenClass {
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
    public String locNameForLangFile() {
        return "Energy Regen";
    }
}
