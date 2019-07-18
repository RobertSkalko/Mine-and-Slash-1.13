package com.robertx22.mine_and_slash.db_lists.newregistry;

import java.util.HashMap;

public class SlashRegistryContainer<C extends ISlashRegistryEntry> {

    private HashMap<String, C> map = new HashMap<>();

    public HashMap<String, C> getAll() {
        return map;
    }

    public C get(String guid) {
        return map.get(guid);
    }

    public boolean isRegistered(C c) {
        return map.containsKey(c.GUID());
    }

    public void register(C c) {
        map.put(c.GUID(), c);
    }
}
