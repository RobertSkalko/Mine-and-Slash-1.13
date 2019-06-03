package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.interfaces.ICrittable;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class CriticalDamageEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.afterThis(new CriticalHitEffect().GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof ICrittable) {

                ICrittable icrit = (ICrittable) Effect;

                if (icrit.GetCrit()) {
                    Effect.Number *= data.getMultiplier();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }
}
