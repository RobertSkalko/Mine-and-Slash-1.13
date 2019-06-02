package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalSpellToAttackDMG;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class ElementalSpellToAttackDMGFlat extends ElementalStatMod {

    public ElementalSpellToAttackDMGFlat(Elements element) {
        super(element);
    }

    @Override
    public String GUID() {
        return "Bonus" + element.name() + "DamageFlat";
    }

    @Override
    public StatMod getStatMod(Elements element) {
        return new ElementalSpellToAttackDMGFlat(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellToAttackDMG(this.element);
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

}
