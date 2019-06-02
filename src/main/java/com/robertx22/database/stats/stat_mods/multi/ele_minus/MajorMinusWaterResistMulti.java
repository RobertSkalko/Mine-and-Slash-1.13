package com.robertx22.database.stats.stat_mods.multi.ele_minus;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.generated.ElementalResist;
import com.robertx22.uncommon.enumclasses.Elements;

public class MajorMinusWaterResistMulti extends BaseMajorEleResistMinus {

    public MajorMinusWaterResistMulti() {
    }

    @Override
    public String GUID() {
        return "MajorMinusWaterResistMulti";
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalResist(Elements.Water);
    }

}
