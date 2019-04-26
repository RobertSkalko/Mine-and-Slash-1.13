package com.robertx22.database.stats.stat_types.elementals.pene;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatEffects.ElementalPeneEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

public class ThunderPene extends BasePene implements IStatEffects {

    public static String GUID = "Thunder Penetration";

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new ElementalPeneEffect());
    }

    public ThunderPene() {
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public Elements Element() {
	return Elements.Thunder;
    }

    @Override
    public String unlocString() {
	return "thunder_penetration";
    }
}
