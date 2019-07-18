package com.robertx22.mine_and_slash.commands.bases;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.initializers.GearTypes;

import java.util.ArrayList;
import java.util.List;

public class GearTypeSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (GearItemSlot slot : GearTypes.All.values()) {
            list.add(slot.GUID());
        }
        list.add("random");

        return list;
    }

}

