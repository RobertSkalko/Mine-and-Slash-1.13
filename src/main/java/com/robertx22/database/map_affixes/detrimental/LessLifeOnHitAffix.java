package com.robertx22.database.map_affixes.detrimental;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.LessLifeOnHitMap;
import com.robertx22.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessLifeOnHitAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "LessLifeOnHitAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new LessLifeOnHitMap(), percent));
    }

}
