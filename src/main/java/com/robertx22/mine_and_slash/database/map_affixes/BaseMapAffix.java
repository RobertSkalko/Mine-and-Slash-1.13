package com.robertx22.mine_and_slash.database.map_affixes;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

import java.util.List;

public abstract class BaseMapAffix implements IWeighted, IGUID, ISlashRegistryEntry<BaseMapAffix> {

    public abstract String GUID();

    public abstract List<StatModData> Stats(int percent);

    @Override
    public int Weight() {
        return this.UncommonWeight;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.MAP_AFFIX;
    }

    public abstract boolean isBeneficial();
}
