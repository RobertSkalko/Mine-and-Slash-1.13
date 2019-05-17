package com.robertx22.database.map_affixes.beneficial.ele_res;

import com.robertx22.database.map_affixes.bases.BaseBeneficialEleAffix;
import com.robertx22.database.map_mods.bonus.ele_res.BonusFireResistMap;
import com.robertx22.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusFireResistAffix extends BaseBeneficialEleAffix {

    @Override
    public String GUID() {
        return "BonusFireResistAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.NewStatusEffect(percent, new BonusFireResistMap()));

    }

}
