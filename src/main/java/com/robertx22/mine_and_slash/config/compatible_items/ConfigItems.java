package com.robertx22.mine_and_slash.config.compatible_items;

import com.robertx22.mine_and_slash.config.IConfig;
import com.robertx22.mine_and_slash.mmorpg.Ref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigItems implements IConfig {

    public ConfigItems() {

        this.map.put("modid:itemid1", new ConfigItem());
        this.map.put("modid:itemid2", new ConfigItem());
    }

    public String ConfigType = "compatible_items";

    public static ConfigItems INSTANCE = new ConfigItems();

    String version = "1.3";

    boolean enabled = true;

    public HashMap<String, ConfigItem> map = new HashMap();

    private List<ConfigItem> list = new ArrayList();

    public void refreshList() {

        list = new ArrayList<>();

        for (Map.Entry<String, ConfigItem> entry : map.entrySet()) {
            entry.getValue().registryName = entry.getKey();
            list.add(entry.getValue());

        }
    }

    public void add(String id, ConfigItem item) {
        map.put(id, item);
    }

    public void addAll(ConfigItems items) {

        if (items.enabled) {
            if (items != null && items.map != null) {
                for (String regname : items.map.keySet()) {
                    System.out.println(Ref.MODID + ":Added Compatible Item: " + regname);
                }
                this.map.putAll(items.map);
                this.refreshList();
            } else {
                System.out.println("File in compatible items folder is not a correct one");
            }
        }
    }

    public List<ConfigItem> getAll() {

        if (list.isEmpty()) {
            refreshList();
        }

        return list;
    }

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

    @Override
    public String GUID() {
        return ConfigType;
    }
}