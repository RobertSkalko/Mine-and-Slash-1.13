package com.robertx22.saveclasses.gearitem;

import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.Sets;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.WornSetData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class SetData {

    @Store
    public String baseSet;

    public Set GetSet() {

        return Sets.All.get(baseSet);
    }

    public List<StatModData> GetAllStats(int level, Unit unit) {

        Set base = GetSet();

        WornSetData data = unit.wornSets.get(this.baseSet);

        List<StatModData> list = new ArrayList<StatModData>();

        for (int i = 0; i < base.getObtainedMods(data).size(); i++) {

            StatMod mod = base.getObtainedMods(data).get(i);

            list.add(StatModData.Load(mod, 100));
        }

        return list;

    }

}
