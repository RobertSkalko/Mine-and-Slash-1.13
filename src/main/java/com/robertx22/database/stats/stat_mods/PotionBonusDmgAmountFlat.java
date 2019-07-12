package com.robertx22.database.stats.stat_mods;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalBonusDmgOnBasic;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class PotionBonusDmgAmountFlat extends ElementalStatMod {

    public PotionBonusDmgAmountFlat(Elements element) {
        super(element);
    }

    @Override
    public String GUID() {
        return element.dmgName.toLowerCase() + "_PotionBonusDmgAmountFlat";
    }

    @Override
    public float Min() {
        return 0;

    }

    @Override
    public float Max() {
        return 100;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalBonusDmgOnBasic(element);
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new PotionBonusDmgAmountFlat(element);
    }
}
