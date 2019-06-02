package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalAffinity;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ElementalAffinityFlat extends ElementalStatMod {

    public ElementalAffinityFlat(Elements element) {
        super(element);

    }

    @Override
    public StatMod getStatMod(Elements element) {
        return new ElementalAffinityFlat(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalAffinity(element);
    }

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 12;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return element.toString() + "_affinity_flat";
    }

}
