package com.robertx22.database.stats.stat_types.elementals.all_damage;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.AllEleDmgEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class AllEleDmg extends Stat implements IStatEffects {

    public static String GUID = "AllEleDmg";

    @Override
    public String locDescForLangFile() {
        return "Increases All Elemental DMG, both spells and attacks";
    }

    public AllEleDmg() {
        this.hasMinimumAmount = false;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new AllEleDmgEffect());
    }

    @Override
    public String locNameForLangFile() {
        return "All Elemental Damage";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.None;
    }
}
