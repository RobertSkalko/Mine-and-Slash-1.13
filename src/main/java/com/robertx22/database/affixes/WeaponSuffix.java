package com.robertx22.database.affixes;

import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class WeaponSuffix extends Suffix implements IGenerated<Suffix> {

    public WeaponTypes type;

    public WeaponSuffix(WeaponTypes type) {
        this.type = type;
    }

    public abstract Suffix newGeneratedInstance(WeaponTypes type);

    @Override
    public List<Suffix> generateAllPossibleStatVariations() {
        List<Suffix> list = new ArrayList<>();
        WeaponTypes.getAll().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;
    }
}
