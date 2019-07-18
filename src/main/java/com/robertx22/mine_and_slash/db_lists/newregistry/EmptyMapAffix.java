package com.robertx22.mine_and_slash.db_lists.newregistry;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.ArrayList;
import java.util.List;

public class EmptyMapAffix extends BaseMapAffix {
    @Override
    public String GUID() {
        return "";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return new ArrayList<>();
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}
