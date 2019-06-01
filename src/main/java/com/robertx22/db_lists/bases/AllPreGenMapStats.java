package com.robertx22.db_lists.bases;

import com.robertx22.database.stats.Stat;
import com.robertx22.db_lists.Stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AllPreGenMapStats {

    private HashMap<Class, PreGenStatSet> map = new HashMap<>();

    public <T> HashSet<T> get(Class<T> theclass) {

        if (map.get(theclass) == null) {

            List<T> list = new ArrayList<>();

            for (Stat stat : Stats.All.values()) {
                if (theclass.isInstance(stat) || stat.getClass().isInstance(theclass)) {
                    list.add((T) stat);
                }

            }

            map.put(theclass, new PreGenStatSet<T>(list));

        }

        return map.get(theclass).list;

    }

}
