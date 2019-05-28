package com.robertx22.database.stats;

import com.robertx22.database.stats.stat_effects.offense.WeaponDamageEffect;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public abstract class WeaponDamageStat extends Stat implements IStatEffects {
    @Override
    public String statDescription() {
        return "Increases damage done if it was caused by that weapon";
    }

    public abstract WeaponTypes weaponType();

    public WeaponDamageStat() {
        this.hasMinimumAmount = false;
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

}
