package com.robertx22.database.map_affixes.detrimental.weapon;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.weapon.LessBowDamageMap;
import com.robertx22.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessBowDamageAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "LessBowDamageAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.NewStatusEffect(percent, new LessBowDamageMap()));

    }

}
