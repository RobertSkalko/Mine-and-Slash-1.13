package com.robertx22.database.stats.stat_effects;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.HealEffect;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class IncreaseHealingEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof HealEffect) {
                Effect.number *= data.getMultiplier();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}