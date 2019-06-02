package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.ElementalPeneEffect;
import com.robertx22.database.stats.stat_types.ElementalStat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ElementalPene extends ElementalStat implements IStatEffects {

    public ElementalPene(Elements element) {
        super(element);
        this.minimumValue = 0;
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new ElementalPene(element);
    }

    @Override
    public String GUID() {
        return this.Element().name() + " Penetration";
    }

    @Override
    public String locDescForLangFile() {
        return "Penetration ignores that much armor";
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
    public String locNameForLangFile() {
        return this.Element().name() + " Penetration";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ElementalPeneEffect());
    }

}
