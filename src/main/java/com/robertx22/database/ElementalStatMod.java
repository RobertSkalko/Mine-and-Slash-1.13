package com.robertx22.database;

import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementalStatMod extends StatMod implements IGenerated<StatMod> {

    public Elements element;

    public ElementalStatMod(Elements element) {
        this.element = element;

    }

    @Override
    public String GUID() {
        return GUID;
    }

    public abstract StatMod getStatMod(Elements element);

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Elements.getAll().forEach(x -> list.add(getStatMod(x)));
        return list;

    }

}