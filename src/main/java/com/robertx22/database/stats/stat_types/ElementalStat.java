package com.robertx22.database.stats.stat_types;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IElementalGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementalStat extends Stat implements IElementalGenerated<Stat> {

    public Elements element;

    public ElementalStat(Elements element) {
        this.element = element;

    }

    @Override
    public Elements Element() {
        return this.element;
    }

    public abstract Stat newGeneratedInstance(Elements element);

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        Elements.getAllExceptNone().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;

    }

}
