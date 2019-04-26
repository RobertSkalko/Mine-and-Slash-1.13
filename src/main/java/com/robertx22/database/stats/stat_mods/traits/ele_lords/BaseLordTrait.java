package com.robertx22.database.stats.stat_mods.traits.ele_lords;

import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.uncommon.interfaces.IWeighted;

public abstract class BaseLordTrait extends BaseTraitMod {

    @Override
    public int Weight() {
	return IWeighted.EpicWeight;
    }

}
