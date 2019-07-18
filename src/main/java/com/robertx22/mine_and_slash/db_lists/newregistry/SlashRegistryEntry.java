package com.robertx22.mine_and_slash.db_lists.newregistry;

import java.util.HashMap;

public class SlashRegistryEntry<C extends IRegistryEntry> {

    private HashMap<String, C> map = new HashMap<>();

    public HashMap<String, C> getAll() {
        return map;
    }

    public C get(String guid) {
        return map.get(guid);
    }

}
