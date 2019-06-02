package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;

import java.util.List;

public interface IGenerated<T extends IGUID> {

    public List<T> generateAllPossibleStatVariations();
}
