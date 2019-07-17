package com.robertx22.mine_and_slash.config.whole_mod_entity_configs;

import java.util.HashMap;

public class ModEntityConfigs {

    public static ModEntityConfigs INSTANCE = new ModEntityConfigs();

    public ModEntityConfigs() {
        specificMobs.put("mob_id", new ModEntityConfig());
        allMobsInAMod.put("modid", new ModEntityConfig());

    }

    public HashMap<String, ModEntityConfig> specificMobs = new HashMap<>();

    public HashMap<String, ModEntityConfig> allMobsInAMod = new HashMap<>();

}
