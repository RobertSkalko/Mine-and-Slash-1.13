package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.bases.IhasRequirements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

import java.util.List;

public abstract class BaseAffix implements IWeighted, IGUID, IAutoLocName, IhasRequirements {

    public BaseAffix() {
    }

    public abstract String GUID();

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

    public abstract List<StatMod> StatMods();

}
