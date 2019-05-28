package com.robertx22.database.stats.stat_types.defense;

import com.robertx22.database.stats.UsableStat;
import com.robertx22.database.stats.stat_effects.defense.ArmorEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class Armor extends UsableStat implements IStatEffects {

    @Override
    public String statDescription() {
        return "Decreases damage taken by a percent";
    }

    public static String GUID = "Armor";

    public Armor() {
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
        return Elements.None;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public float MaximumPercent() {
        return 0.75F;
    }

    @Override
    public int AverageStat() {
        return 10;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ArmorEffect());
    }

}
