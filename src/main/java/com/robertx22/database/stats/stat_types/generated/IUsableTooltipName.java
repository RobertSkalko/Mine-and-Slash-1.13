package com.robertx22.database.stats.stat_types.generated;

public interface IUsableTooltipName {

    public String getTheName();

    public default String getUsableNameForTooltip() {
        return getTheName().replaceAll("_", " ");
    }
}
