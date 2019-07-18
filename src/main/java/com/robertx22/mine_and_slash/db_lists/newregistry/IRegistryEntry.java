package com.robertx22.mine_and_slash.db_lists.newregistry;

import com.robertx22.mine_and_slash.database.IGUID;

public interface IRegistryEntry<C> extends IGUID {
    RegistryEntryType getRegistryType();

    // this could be used for stuff like setdata, affixdata etc?  prototype
    default C getFromRegistry() {
        return (C) SlashRegistry.test.getAll().get(this.GUID());

    }
}
