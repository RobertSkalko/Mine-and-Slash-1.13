package com.robertx22.mine_and_slash.database.unique_items;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.interfaces.*;

import java.util.List;

public interface IUnique extends IWeighted, ITiered, IGUID, IAutoLocName, IAutoLocDesc, IGearItem {

    @Override
    public default int Weight() {
        return this.UncommonWeight;
    }

    List<StatMod> uniqueStats();

    String slot();

    public default boolean canGetSet() {
        return false;
    }

    @Override
    public default AutoLocGroup locNameGroup() {
        return AutoLocGroup.Unique_Items;
    }

    @Override
    public default AutoLocGroup locDescGroup() {
        return AutoLocGroup.Unique_Items;
    }

}
