package com.robertx22.database.stats.stat_types.elementals.spell_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class SpellNatureDamage extends BaseSpellDamage {
    public static String GUID = "Spell Nature Damage";

    public SpellNatureDamage() {
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
        return Elements.Nature;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

}
