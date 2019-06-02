package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.XElementAffinity;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class XElementAffinityFlat extends StatMod implements IGenerated<StatMod> {

    public Elements element;

    public XElementAffinityFlat() {
    }

    public XElementAffinityFlat(Elements element) {
        this.element = element;

    }

    @Override
    public Stat GetBaseStat() {
        return new XElementAffinity(element);
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

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Elements.getAll().forEach(x -> list.add(new XElementAffinityFlat(x)));
        return list;

    }
}
