package com.robertx22.database.stats;

import com.robertx22.database.stats.stat_types.BaseTrait;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.capability.EntityCap.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;
import com.robertx22.uncommon.interfaces.IWeighted;

public abstract class Trait extends BaseTrait implements IAffectsOtherStats {

    @Override
    public String locDescForLangFile() {
        return "";
    }

    @Override
    public void TryAffectOtherStats(UnitData unit, StatData data) {
        if (this.condition(unit)) {
            for (StatModData mod : getStatsMods()) {
                mod.useOnPlayer(unit, unit.getLevel());
            }
        }

    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
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
