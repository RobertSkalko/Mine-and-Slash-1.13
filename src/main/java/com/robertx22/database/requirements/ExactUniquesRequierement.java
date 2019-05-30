package com.robertx22.database.requirements;

import com.robertx22.database.unique_items.IUnique;

import java.util.ArrayList;
import java.util.List;

public class ExactUniquesRequierement extends UniqueItemRequirement {

    List<IUnique> uniques = new ArrayList<>();

    public ExactUniquesRequierement(IUnique unique) {
        uniques.add(unique);
    }

    public ExactUniquesRequierement(List<IUnique> uniques) {
        uniques.addAll(uniques);
    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        Boolean prev = super.meetsRequierment(requested);

        if (prev == false) {
            return false;
        }

        if (uniques.contains(requested.gearData.uniqueStats.getUniqueItem()) == false) {
            return false;
        }

        return true;

    }
}


