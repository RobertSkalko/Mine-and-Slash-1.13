package com.robertx22.database.map_affixes.detrimental;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessWeaponDamageMapAffix extends DetrimentalMapAffix implements IGenerated<BaseMapAffix> {

    public WeaponTypes weaponType;
    public String GUID;

    public LessWeaponDamageMapAffix() {

    }

    public LessWeaponDamageMapAffix(WeaponTypes type) {
        this.weaponType = type;
        this.GUID = "Less" + this.weaponType.name() + "DamageAffix";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new WeaponDamageFlat(weaponType), percent));

    }

    @Override
    public List<BaseMapAffix> generateAllPossibleStatVariations() {

        List<BaseMapAffix> list = new ArrayList<>();
        WeaponTypes.getAll().forEach(x -> list.add(new LessWeaponDamageMapAffix(x)));
        return list;
    }
}
