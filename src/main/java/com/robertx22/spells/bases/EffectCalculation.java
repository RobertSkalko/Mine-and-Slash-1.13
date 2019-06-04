package com.robertx22.spells.bases;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.Unit;

public class EffectCalculation {

    public Stat stat;
    public float Multi;

    public EffectCalculation(Stat stat, float multi) {
        super();
        this.stat = stat;
        Multi = multi;
    }

    public Stat GetStat() {
        return stat;
    }

    public int GetValue(Unit unit) {
        return (int) (unit.MyStats.get(stat.GUID()).Value * Multi);
    }

}
