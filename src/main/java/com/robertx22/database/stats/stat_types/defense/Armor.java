package com.robertx22.database.stats.stat_types.defense;

import com.robertx22.database.stats.IUsableStat;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.defense.ArmorEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class Armor extends Stat implements IStatEffects, IUsableStat {

    @Override
    public String locDescForLangFile() {
        return "Decreases damage taken by a percent";
    }

    public static String GUID = "Armor";

    public Armor() {
        this.minimumValue = 0;
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

    @Override
    public String locNameForLangFile() {
        return "Armor";
    }
}
