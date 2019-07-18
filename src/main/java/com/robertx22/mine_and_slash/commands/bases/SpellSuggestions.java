package com.robertx22.mine_and_slash.commands.bases;

import com.robertx22.mine_and_slash.db_lists.initializers.Spells;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;

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

