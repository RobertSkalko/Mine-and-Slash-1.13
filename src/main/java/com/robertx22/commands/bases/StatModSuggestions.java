package com.robertx22.commands.bases;

import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.StatMods;

import java.util.ArrayList;
import java.util.List;

public class StatModSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (StatMod item : StatMods.All.values()) {
            list.add(item.GUID());
        }

        return list;

    }

}

