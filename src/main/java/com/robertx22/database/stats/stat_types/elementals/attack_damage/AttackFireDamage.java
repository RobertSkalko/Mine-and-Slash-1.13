package com.robertx22.database.stats.stat_types.elementals.attack_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AttackFireDamage extends BaseElementalAttackDamage {
    public static String GUID = "Attack Fire Damage";

    public AttackFireDamage() {
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Fire;
    }

}
