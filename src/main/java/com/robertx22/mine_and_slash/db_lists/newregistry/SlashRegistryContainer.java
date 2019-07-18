package com.robertx22.mine_and_slash.db_lists.newregistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlashRegistryContainer<C extends ISlashRegistryEntry> {

    public static List<String> errorsAlertedFor = new ArrayList<>();

    public SlashRegistryContainer(SlashRegistryType type, C empty) {
        this.type = type;
        this.empty = empty;
    }

    private SlashRegistryType type;
    private C empty;

    private HashMap<String, C> map = new HashMap<>();

    public HashMap<String, C> getAll() {
        return map;
    }

    public List<C> getList() {
        return new ArrayList<C>(map.values());
    }

    public C get(String guid) {

        if (guid.isEmpty()) {
            return empty;
        }
        if (map.containsKey(guid)) {
            return map.get(guid);
        } else {
            System.out.println("Mine and Slash GUID: " + guid + " of type: " + type.toString() + " doesn't exist. This is either a removed/renamed old registry, or robertx22 forgot to include it in an update.");
            return empty;
        }
    }

    public boolean isRegistered(C c) {
        return map.containsKey(c.GUID());
    }

    public void register(C c) {

        if (isRegistered(c)) {
            if (errorsAlertedFor.contains(c.GUID()) == false) {
                System.out.println("[Mine and Slash Registry Error]: Key: " + c.GUID() + " has already been registered to: " + c
                        .getSlashRegistryType()
                        .toString() + " registry.");
                errorsAlertedFor.add(c.GUID());
            }

        } else {
            map.put(c.GUID(), c);
        }

    }
}
