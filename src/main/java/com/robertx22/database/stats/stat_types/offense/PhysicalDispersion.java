package com.robertx22.database.stats.stat_types.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.PhysicalToHighestEle;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class PhysicalDispersion extends Stat implements IStatEffects {

    public PhysicalDispersion() {
        this.maximumValue = 100;
    }

    public static String GUID = "physical_dispersion";

    @Override
    public String locDescForLangFile() {
        return "If you are doing elemental dmg, transfers physical damage to elemental damage of highest amount.";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Misc;
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
        return null;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    static final PhysicalToHighestEle effect = new PhysicalToHighestEle();

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(effect);
    }

    @Override
    public String locNameForLangFile() {
        return "Physical Dispersion";
    }
}

