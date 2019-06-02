package com.robertx22.uncommon.utilityclasses;

import com.robertx22.uncommon.interfaces.ITiered;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T extends ITiered> List<T> SameTierOrLess(Iterable<T> coll, int tier) {

        List<T> list = new ArrayList<T>();
        for (ITiered tiered : coll) {
            if (tiered.Tier() <= tier) {
                list.add((T) tiered);
            }
        }

        return list;

    }

    public static <T> List<T> newList(List<T> list, T t) {
        list.add(t);
        return list;

    }

    public static <T> List<T> newList(List<T> list, T one, T two) {
        list.add(one);
        list.add(two);
        return list;

    }

}
