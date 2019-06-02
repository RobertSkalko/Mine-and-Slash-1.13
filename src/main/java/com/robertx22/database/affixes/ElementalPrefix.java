package com.robertx22.database.affixes;

import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IElementalGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementalPrefix extends Prefix implements IElementalGenerated<Prefix> {

    public Elements element;

    public ElementalPrefix(Elements element) {
        this.element = element;

    }

    public Elements Element() {
        return this.element;
    }

    public abstract Prefix newGeneratedInstance(Elements element);

    @Override
    public List<Prefix> generateAllPossibleStatVariations() {
        List<Prefix> list = new ArrayList<>();
        Elements.getAll().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;

    }

}
