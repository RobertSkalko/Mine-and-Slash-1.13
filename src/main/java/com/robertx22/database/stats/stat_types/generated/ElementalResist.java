package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.IUsableStat;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.defense.ElementalResistEffect;
import com.robertx22.database.stats.stat_types.ElementalStat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ElementalResist extends ElementalStat implements IStatEffects, IUsableStat {

    public ElementalResist(Elements element) {
        super(element);
        this.minimumValue = 0;
    }

    @Override
    public Stat newStatInstance(Elements element) {
        return new ElementalResist(element);
    }

    @Override
    public String GUID() {
        return this.Element().name() + " Resist";
    }

    @Override
    public String locDescForLangFile() {
        return "Stops a % damage of that element";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ElementalResistEffect());
    }

    @Override
    public float MaximumPercent() {
        return 0.75F;
    }

    @Override
    public int AverageStat() {
        return 3;
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
        return this.Element().name() + " Resist";
    }

}

