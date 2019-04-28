package com.robertx22.mmorpg.config.non_mine_items;

import java.util.HashMap;
import java.util.Map;

public class ConfigItems {

    public ConfigItems() {

        this.map.put("modid:itemid1", new ConfigItem());
        this.map.put("modid:itemid2", new ConfigItem());
    }

    public static ConfigItems INSTANCE = new ConfigItems();

    String version = "1.0";

    public HashMap<String, ConfigItem> map = new HashMap();

    public void validateAll() {

        try {

            for (Map.Entry<String, ConfigItem> entry : map.entrySet()) {
                if (entry.getValue().isValid() == false) {

                    System.out.println(entry.getKey() + " Is not correctly implemented. Please go to CompatibleItems file and fix it");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
