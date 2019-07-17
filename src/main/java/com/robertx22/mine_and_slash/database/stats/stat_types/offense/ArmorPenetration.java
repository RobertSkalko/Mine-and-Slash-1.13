package com.robertx22.mine_and_slash.database.stats.stat_types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.offense.PenetrationEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ArmorPenetration extends Stat implements IStatEffects {
    public static String GUID = "Armor Penetration";

    @Override
    public StatGroup statGroup() {
        return StatGroup.Penetration;
    }

    @Override
    public String locDescForLangFile() {
        return "Ignores x armor";
    }

    public ArmorPenetration() {
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
        return false;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new PenetrationEffect());
    }

    @Override
    public String locNameForLangFile() {
        return "Armor Penetration";
    }
}