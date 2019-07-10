package com.robertx22.saveclasses;

import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.Sets;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.capability.EntityData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.HashMap;
import java.util.Map;

@Storable
public class WornSetsContainerData {

    public WornSetsContainerData() {

    }

    @Store
    private HashMap<String, WornSetData> map = new HashMap<>();

    public WornSetData get(String guid) {

        if (Sets.All.containsKey(guid)) {

            if (map.containsKey(guid) == false) {
                map.put(guid, new WornSetData(Sets.All.get(guid)));
            }

            return map.get(guid);
        } else {
            return new WornSetData();
        }

    }

    public void addSet(GearItemData gear) {

        if (gear.set == null || gear.set.baseSet == null || gear.set.GetSet() == null) {
            return;
        }

        String key = gear.set.baseSet;
        if (map.containsKey(key) == false) {
            map.put(key, new WornSetData(gear.set.GetSet()));
        }

        WornSetData data = map.get(key);
        data.addSet(gear);
        map.put(key, data);

    }

    public void AddAllSetStats(EntityData.UnitData unitdata) {

        for (Map.Entry<String, WornSetData> entry : this.map.entrySet()) {

            Set set = entry.getValue().getSet();

            if (set != null) {

                for (StatMod mod : entry.getValue().getSetStats()) {

                    StatModData.Load(mod, set.StatPercent)
                            .useOnPlayer(unitdata, entry.getValue().getAverageLevel());

                }
            }
        }
    }

}
