package com.robertx22.database.map_affixes;

import com.robertx22.database.IGUID;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.List;

public abstract class BaseMapAffix implements IWeighted, IGUID {

    public abstract String GUID();

    public abstract List<StatModData> Stats(int percent);

    @Override
    public int Weight() {
        return this.UncommonWeight;
    }

    public abstract boolean isBeneficial();
}
