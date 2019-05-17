package com.robertx22.database.stats.stat_types.elementals.pene;

import com.robertx22.database.stats.stat_effects.ElementalPeneEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ThunderPene extends BasePene implements IStatEffects {

    public static String GUID = "Thunder Penetration";

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ElementalPeneEffect());
    }

    public ThunderPene() {
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

}
