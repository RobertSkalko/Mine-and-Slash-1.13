package com.robertx22.saveclasses;

import com.robertx22.db_lists.StatMods;
import com.robertx22.saveclasses.gearitem.StatModData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.HashMap;

@Storable
public class CustomStatsData {

    @Store
    public HashMap<String, StatModData> stats = new HashMap<>();

    public void add(String hashmapGUID, String statModGUID, int percent, float multi) {

        try {
            stats.put(hashmapGUID, StatModData.Load(StatMods.All.get(statModGUID)
                    .multi(multi), percent));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void remove(String hashmapGUID) {
        stats.remove(hashmapGUID);
    }

}
