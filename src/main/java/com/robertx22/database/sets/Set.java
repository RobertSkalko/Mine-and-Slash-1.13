package com.robertx22.database.sets;

import com.robertx22.database.IGUID;
import com.robertx22.database.requirements.GearRequestedFor;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.bases.IhasRequirements;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class Set implements IWeighted, IGUID, IhasRequirements {

    public Set() {
    }

    public abstract String Name();

    @Override
    public String GUID() {
        return Name();
    }

    public int StatPercent = 100;

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

    public abstract HashMap<Integer, StatMod> AllMods();

    public boolean meetsRequirements(GearRequestedFor requested) {

        return requirements().satisfiesAllRequirements(requested);

    }

    public List<StatMod> GetObtainedMods(Unit unit) {

        List<StatMod> mods = new ArrayList();

        for (Entry<Integer, StatMod> mod : this.AllMods().entrySet()) {

            if (unit.WornSets.get(this.GUID()) >= mod.getKey()) {
                mods.add(mod.getValue());
            }

        }

        return mods;
    }

}
