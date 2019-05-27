package com.robertx22.commands.bases;

import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.UniqueItems;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class UniqueItemsSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (Item item : UniqueItems.ITEMS.values()) {
            IUnique uniq = (IUnique) item;
            list.add(uniq.GUID());
        }

        return list;
    }

}

