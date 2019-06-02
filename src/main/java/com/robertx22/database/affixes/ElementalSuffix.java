package com.robertx22.database.affixes;

import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementalSuffix extends Suffix implements IGenerated<Suffix> {

    public Elements element;

    public ElementalSuffix(Elements element) {
        this.element = element;

    }

    public Elements Element() {
        return this.element;
    }

    public abstract Suffix newStatInstance(Elements element);

    @Override
    public List<Suffix> generateAllPossibleStatVariations() {
        List<Suffix> list = new ArrayList<>();
        Elements.getAll().forEach(x -> list.add(newStatInstance(x)));
        return list;

    }

}

