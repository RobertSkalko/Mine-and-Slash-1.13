package com.robertx22.database.stats.stat_types.defense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.defense.SpellDodgeEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class SpellDodge extends Stat implements IStatEffects {
    public static String GUID = "Spell Dodge";

    @Override
    public String locDescForLangFile() {
        return "Chance to Ignore spell damage";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new SpellDodgeEffect());
    }

    public SpellDodge() {
    }

    @Override
    public String GUID() {
        return GUID;
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
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String locNameForLangFile() {
        return "Spell Dodge";
    }
}
