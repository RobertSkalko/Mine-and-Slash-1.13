package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalPene;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

import java.util.ArrayList;
import java.util.List;

public class ElementalFocusFlat extends ElementalStatMod {

    public ElementalFocusFlat(Elements element) {
        super(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalPene(this.element);
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 25;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return element.name() + "focus_flat";
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalFocusFlat(element);
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Elements.getAllSingleElements().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;
    }

}

