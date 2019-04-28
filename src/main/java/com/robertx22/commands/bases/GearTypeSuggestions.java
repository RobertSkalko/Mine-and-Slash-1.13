package com.robertx22.commands.bases;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;

public class GearTypeSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (GearItemSlot slot : GearTypes.All.values()) {
            list.add(slot.GUID());
        }


        return list;
    }

}
