package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class UniqueItems implements ISlashRegistryInit {

    public static HashMap<String, Item> ITEMS = new HashMap<String, Item>();

    public static List<IUnique> getAll() {
        return SlashRegistry.Uniques().getList();
    }

    public static List<IUnique> getAllUniquesOfTier(int tier, Collection<Item> coll) {
        List<IUnique> list = new ArrayList<IUnique>();

        for (Item item : coll) {
            IUnique baseu = (IUnique) item;

            if (tier == baseu.Tier()) {
                list.add((IUnique) item);
            }
        }
        return list;
    }

    public static List<IUnique> getAllPossibleUniqueDrops(int tier,
                                                          Collection<Item> coll) {
        List<IUnique> list = new ArrayList<IUnique>();

        for (Item item : coll) {
            IUnique baseu = (IUnique) item;

            if (tier >= baseu.Tier()) {
                list.add((IUnique) item);
            }
        }
        return list;
    }

    public static List<IUnique> filterUniquesByType(String type, List<IUnique> coll) {

        List<IUnique> list = new ArrayList<IUnique>();

        for (IUnique item : coll) {
            if (item.slot().equals(type) || type.equals("random") || type.equals("")) {
                list.add((IUnique) item);
            }
        }

        return list;
    }

    @Override
    public void registerAll() {

        List<IUnique> list = new ArrayList<>();
        if (list.isEmpty()) {
            for (Item item : ITEMS.values()) {
                IUnique uniq = (IUnique) item;
                list.add(uniq);
            }

        }

        list.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.UNIQUE_ITEM)
                .register(x));

    }
}
