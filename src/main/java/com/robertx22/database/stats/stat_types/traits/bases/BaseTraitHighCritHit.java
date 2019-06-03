package com.robertx22.database.stats.stat_types.traits.bases;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.offense.CriticalHit;

public abstract class BaseTraitHighCritHit extends ConditionalTrait {

    @Override
    public Stat stat() {
        return new CriticalHit();
    }

    @Override
    public int amount() {
        return 30;
    }

    @Override
    public boolean isPercent() {
        return true;
    }

    @Override
    public MoreOrLess moreOrLess() {
        return MoreOrLess.More;
    }
}
