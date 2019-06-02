package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.ArrayList;
import java.util.List;

public interface IElementalGenerated<T extends IGUID> extends IGenerated {

    public abstract T newGeneratedInstance(Elements element);

    @Override
    public default List<T> generateAllPossibleStatVariations() {
        List<T> list = new ArrayList<>();
        Elements.getAllExceptNone().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;

    }
}
