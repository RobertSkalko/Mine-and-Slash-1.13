package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.ElementalAttackDamageEffect;
import com.robertx22.database.stats.stat_types.ElementalStat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ElementalAttackDamage extends ElementalStat implements IStatEffects {

    public ElementalAttackDamage(Elements element) {
        super(element);
    }

    @Override
    public Stat newStatInstance(Elements element) {
        return new ElementalSpellToAttackDMG(element);
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ElementalAttackDamageEffect());
    }

    @Override
    public String locNameForLangFile() {
        return "Attack " + this.Element().name() + " Damage";
    }

    @Override
    public String locDescForLangFile() {
        return "Adds x element damage on weapon hit";
    }

    @Override
    public String GUID() {
        return "Attack " + Element().name() + " Damage";
    }
}
