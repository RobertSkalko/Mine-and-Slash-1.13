package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.WeaponDamageEffect;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeaponDamage extends Stat implements IStatEffects, IGenerated<Stat> {

    private WeaponTypes weaponType;
    public String GUID;

    public WeaponDamage() {
    }

    public WeaponDamage(WeaponTypes type) {
        this.weaponType = type;
        this.GUID = this.weaponType.name() + " Damage";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases damage done if it was caused by that weapon";
    }

    public WeaponTypes weaponType() {
        return this.weaponType;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new WeaponDamageEffect());
    }

    @Override
    public String locNameForLangFile() {
        return this.weaponType().name() + " Damage";
    }

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        WeaponTypes.getAll().forEach(x -> list.add(new WeaponDamage(x)));
        return list;

    }
}
