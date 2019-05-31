package com.robertx22.database.stats.stat_types.elementals.resist;

import com.robertx22.database.stats.UsableStat;
import com.robertx22.database.stats.stat_effects.defense.AllEleResistEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class AllEleResist extends UsableStat implements IStatEffects {

    public AllEleResist() {
        this.minimumValue = 0;
    }

    @Override
    public String locDescForLangFile() {
        return "Stops a % of elemental damage";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new AllEleResistEffect());
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
    public Elements Element() {
        return Elements.None;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Elemental Resist";
    }

    @Override
    public String GUID() {
        return "all_elemental_resist";
    }
}
