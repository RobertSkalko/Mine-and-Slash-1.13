package com.robertx22.database.stats.stat_types;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementalStat extends Stat implements IGenerated<Stat> {

    Elements element;

    public ElementalStat(Elements element) {
        this.element = element;

    }

    @Override
    public Elements Element() {
        return this.element;
    }

    public abstract Stat newStatInstance(Elements element);

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        Elements.getAll().forEach(x -> list.add(newStatInstance(x)));
        return list;

    }

}
