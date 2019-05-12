package com.robertx22.database.stats.stat_types.elementals.spell_to_attack;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.SpellToBasicDamageEffect;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public abstract class BaseSpellToBasicDamage extends Stat implements IStatEffects {

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new SpellToBasicDamageEffect());
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    public abstract Stat StatThatGiveDamage();

}
