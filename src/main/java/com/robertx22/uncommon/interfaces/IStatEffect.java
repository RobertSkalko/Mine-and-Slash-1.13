package com.robertx22.uncommon.interfaces;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;

public interface IStatEffect {

    public enum Priority {
        First(0),
        Second(1),
        Third(2),
        Fourth(3),
        Fifth(4),
        AlmostLast(99),
        Last(100);

        Priority(int priority) {
            this.priority = priority;
        }

        public int priority = 0;

        public static int afterThis(int other) {
            return other + 1;

        }

        public static int beforeThis(int other) {
            return other - 1;

        }
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
