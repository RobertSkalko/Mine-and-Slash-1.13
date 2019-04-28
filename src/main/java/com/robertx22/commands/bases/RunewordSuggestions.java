package com.robertx22.commands.bases;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.RuneWords;

import java.util.ArrayList;
import java.util.List;

public class RunewordSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (RuneWord item : RuneWords.All.values()) {
            list.add(item.GUID());
        }

        return list;
    }

}

