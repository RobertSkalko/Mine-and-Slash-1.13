package com.robertx22.database.stats.stat_types.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.AllSpellDamageEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class SpellDamage extends Stat implements IStatEffects {

    public static String GUID = "spell_damage";

    @Override
    public String statDescription() {
        return "Increases DMG of all spells no matter the element";
    }

    public SpellDamage() {

    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    static final AllSpellDamageEffect effect = new AllSpellDamageEffect();

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(effect);
    }
}
