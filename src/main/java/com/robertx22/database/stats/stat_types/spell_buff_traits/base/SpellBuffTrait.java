package com.robertx22.database.stats.stat_types.spell_buff_traits.base;

import com.robertx22.database.stats.stat_types.BaseTrait;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffects;

public abstract class SpellBuffTrait extends BaseTrait implements IStatEffects {

    @Override
    public int Weight() {
        return 0;
    }

    // override if it has a condition
    public boolean condition(UnitData unit) {
        return true;
    }

    @Override
    public Elements Element() {
        return null;
    }

}
