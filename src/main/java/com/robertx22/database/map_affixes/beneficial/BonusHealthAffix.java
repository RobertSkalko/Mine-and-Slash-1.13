package com.robertx22.database.map_affixes.beneficial;

import com.robertx22.database.map_affixes.BeneficialMapAffix;
import com.robertx22.database.map_mods.bonus.BonusHealthMap;
import com.robertx22.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusHealthAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "Bonus Health";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new BonusHealthMap(), percent));
    }

}
