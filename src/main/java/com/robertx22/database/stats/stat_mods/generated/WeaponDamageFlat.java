package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.WeaponDamage;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class WeaponDamageFlat extends StatMod implements IGenerated<StatMod> {

    public WeaponTypes weaponType;

    public WeaponDamageFlat(WeaponTypes type) {
        this.weaponType = type;
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
    public Stat GetBaseStat() {
        return new WeaponDamage(weaponType);
    }

    @Override
    public String GUID() {
        return weaponType.name() + "DamageFlat";
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        WeaponTypes.getAll().forEach(x -> list.add(new WeaponDamageFlat(x)));
        return list;

    }

}
