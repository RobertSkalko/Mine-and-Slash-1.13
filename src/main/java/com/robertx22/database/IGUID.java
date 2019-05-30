package com.robertx22.database;

public interface IGUID {

    public String GUID();

    public default String formattedGUID() {
        return GUID().toLowerCase().replaceAll(" ", "_");
    }
}
