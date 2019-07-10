package com.robertx22.database.sets;

import com.robertx22.database.IGUID;
import com.robertx22.database.MinMax;
import com.robertx22.database.requirements.GearRequestedFor;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.bases.IhasRequirements;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.WornSetData;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class Set implements IWeighted, IGUID, IhasRequirements, IAutoLocName {

    public Set() {
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".set." + formattedGUID();
    }

    public int StatPercent = 100;

    public MinMax statPercents = new MinMax(100, 100);

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Item_Sets;
    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

    public abstract HashMap<Integer, StatMod> AllMods();

    public boolean meetsRequirements(GearRequestedFor requested) {

        return requirements().satisfiesAllRequirements(requested);

    }

    public List<StatMod> getObtainedMods(WornSetData data) {

        List<StatMod> mods = new ArrayList();

        if (data.setGUID == this.GUID()) {
            for (Entry<Integer, StatMod> mod : this.AllMods().entrySet()) {
                if (data.count >= mod.getKey()) {
                    mods.add(mod.getValue());
                }
            }
        }

        return mods;
    }

}
