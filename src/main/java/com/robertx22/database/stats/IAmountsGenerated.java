package com.robertx22.database.stats;

import com.robertx22.database.IGUID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IAmountsGenerated extends IGUID {

    public default String getGUIDPrefix() {
        if (this.modSize().equals(StatModSizes.Medium)) {
            return "";
        } else {
            return this.modSize().name();
        }
    }

    public StatModSizes modSize();

    StatMod setSize(StatModSizes size);

    //StatModSizes getSize();

    public default List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Arrays.asList(StatModSizes.values()).forEach(x -> list.add());

        return list;
    }
}
