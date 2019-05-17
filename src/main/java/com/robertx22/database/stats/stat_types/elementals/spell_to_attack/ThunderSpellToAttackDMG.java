package com.robertx22.database.stats.stat_types.elementals.spell_to_attack;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderSpellToAttackDMG extends BaseSpellToBasicDamage {
    public static String GUID = "Thunder Spell to Attack DMG";

    public ThunderSpellToAttackDMG() {

    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

    @Override
    public Stat StatThatGiveDamage() {
        return new AttackThunderDamage();
    }

}