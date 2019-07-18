package com.robertx22.mine_and_slash.db_lists.registry;

import com.robertx22.mine_and_slash.database.IGUID;

public interface ISlashRegistryEntry<C> extends IGUID {

    SlashRegistryType getSlashRegistryType();

    // this could be used for stuff like setdata, affixdata etc?  prototype
    default C getFromRegistry() {
        return (C) SlashRegistry.get(getSlashRegistryType(), this.GUID());
    }

}
