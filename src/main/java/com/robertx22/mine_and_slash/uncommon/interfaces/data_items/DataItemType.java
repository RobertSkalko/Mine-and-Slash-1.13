package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

public enum DataItemType {

    GEAR("gear"),
    SPELL("spell"),
    RUNE("rune"),
    MAP("map");

    DataItemType(String nbtGUID) {
        this.nbtGUID = nbtGUID;
    }

    public String nbtGUID;
}
