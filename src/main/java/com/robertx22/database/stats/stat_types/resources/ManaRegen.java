package com.robertx22.database.stats.stat_types.resources;

public class ManaRegen extends BaseRegenClass {
    public static String GUID = "Mana Regen";

    public ManaRegen() {

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
        return "Mana Regen";
    }
}
