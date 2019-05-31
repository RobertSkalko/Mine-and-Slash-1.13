package com.robertx22.database.stats.stat_types.elementals.all_damage;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.AllEleDmgEffectIfElement;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public abstract class AllEleDamageBase extends Stat implements IStatEffects {

    @Override
    public String locDescForLangFile() {
        return "Increases All DMG of that element, both spells and attacks";
    }

    public AllEleDamageBase() {
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new AllEleDmgEffectIfElement());
    }

    @Override
    public String locNameForLangFile() {
        return "All" + this.Element().name() + " Damage";
    }

}
