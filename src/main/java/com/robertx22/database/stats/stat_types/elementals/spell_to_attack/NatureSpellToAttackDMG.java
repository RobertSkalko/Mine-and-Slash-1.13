package com.robertx22.database.stats.stat_types.elementals.spell_to_attack;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.uncommon.enumclasses.Elements;

public class NatureSpellToAttackDMG extends BaseSpellToBasicDamage {
    public static String GUID = "Nature Spell to Attack DMG";

    public NatureSpellToAttackDMG() {

    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Nature;
    }

    @Override
    public Stat StatThatGiveDamage() {
        return new SpellNatureDamage();
    }

}