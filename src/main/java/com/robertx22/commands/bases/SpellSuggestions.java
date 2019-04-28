package com.robertx22.commands.bases;

import com.robertx22.db_lists.Spells;
import com.robertx22.spells.bases.BaseSpell;

import java.util.ArrayList;
import java.util.List;

public class SpellSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (BaseSpell spell : Spells.All.values()) {
            list.add(spell.GUID());
        }
        list.add("random");

        return list;
    }

}

