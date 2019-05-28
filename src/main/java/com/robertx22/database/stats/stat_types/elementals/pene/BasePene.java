package com.robertx22.database.stats.stat_types.elementals.pene;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.interfaces.IStatEffects;

public abstract class BasePene extends Stat implements IStatEffects {
    @Override
    public String statDescription() {
        return "Penetration ignores that much armor";
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

}
