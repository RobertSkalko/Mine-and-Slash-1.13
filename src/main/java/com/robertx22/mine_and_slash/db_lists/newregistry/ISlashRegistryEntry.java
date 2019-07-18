package com.robertx22.mine_and_slash.db_lists.newregistry;

import com.robertx22.mine_and_slash.database.IGUID;

public interface ISlashRegistryEntry<C> extends IGUID {

    SlashRegistryType getSlashRegistryType();

    // this could be used for stuff like setdata, affixdata etc?  prototype
    default C getFromRegistry() {
        return (C) SlashRegistry.get(getSlashRegistryType(), this.GUID());
    }

    public default void registerToSlashRegistry() {

        SlashRegistryContainer registry = SlashRegistry.getRegistry(getSlashRegistryType());

        if (registry.isRegistered(this)) {
            System.out.println("[Mine and Slash Registry Error]: Key: " + this.GUID() + " has already been registered to: " + this
                    .getSlashRegistryType()
                    .toString() + " registry.");
        } else {
            registry.register(this);
        }

    }
}
