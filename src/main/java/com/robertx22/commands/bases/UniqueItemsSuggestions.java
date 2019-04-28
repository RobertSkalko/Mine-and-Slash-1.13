package com.robertx22.commands.bases;

import com.robertx22.items.unique_items.IUnique;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class UniqueItemsSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (Item item : IUnique.ITEMS.values()) {
            IUnique uniq = (IUnique) item;
            list.add(uniq.GUID());
        }

        return list;
    }

}

