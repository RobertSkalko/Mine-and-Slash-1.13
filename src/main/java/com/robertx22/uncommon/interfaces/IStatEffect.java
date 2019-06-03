package com.robertx22.uncommon.interfaces;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;

public interface IStatEffect {

    public enum Priority {
        First,
        Second,
        Third,
        Fourth,
        Fifth,
        Last
    }

    public enum EffectSides {
        Source,
        Target
    }

    public abstract EffectSides Side();

    public abstract int GetPriority();

    public abstract EffectData TryModifyEffect(EffectData Effect, Unit Source,
                                               StatData statData, Stat stat);

}
