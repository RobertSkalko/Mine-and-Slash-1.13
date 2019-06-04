package com.robertx22.database;

import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IElementalGenerated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ElementalStatMod extends StatMod implements IElementalGenerated<StatMod> {

    public Elements element;

    public ElementalStatMod(Elements element) {
        this.element = element;

    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Elements.getAllExceptNone().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;

    }

    public List<StatMod> allSingleElementVariations() {

        return generateAllPossibleStatVariations().stream()
                .filter(x -> ((ElementalStatMod) x).element.isSingleElement)
                .collect(Collectors.toList());
    }
}
