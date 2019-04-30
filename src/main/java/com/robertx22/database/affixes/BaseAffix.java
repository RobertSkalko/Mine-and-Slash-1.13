package com.robertx22.database.affixes;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.List;

public abstract class BaseAffix implements IWeighted, IGUID {

    public BaseAffix() {
    }

    abstract public String locName();

    public abstract String GUID();

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

    public abstract List<StatMod> StatMods();

}