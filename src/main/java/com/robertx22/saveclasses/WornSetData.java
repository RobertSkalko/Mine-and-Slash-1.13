package com.robertx22.saveclasses;

import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.Sets;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class WornSetData {

    public WornSetData() {

    }

    public WornSetData(Set set) {
        this.setGUID = set.GUID();
    }

    @Store
    public String setGUID = "";
    @Store
    public int count = 0;
    @Store
    private List<Integer> lvls = new ArrayList<>();

    public Set getSet() {

        if (Sets.All.containsKey(setGUID)) {
            return Sets.All.get(setGUID);
        }
        return null;

    }

    public List<StatMod> getSetStats() {
        if (Sets.All.containsKey(setGUID)) {
            return Sets.All.get(setGUID).getObtainedMods(this);
        } else {
            return new ArrayList<>();
        }
    }

    public void addSet(GearItemData gear) {

        if (gear.set == null || gear.set.baseSet == null) {
            return;
        }

        if (gear.set.baseSet.equals(setGUID)) {
            lvls.add(gear.level);
            count++;
        }
    }

    public int getAverageLevel() {

        int sum = lvls.stream().mapToInt(Integer::intValue).sum();

        if (sum > 0) {
            return sum / lvls.size();
        } else {
            return 0;
        }
    }
}
