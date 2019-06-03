package com.robertx22.database.stats.stat_effects.defense;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class DodgeEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.AlmostLast.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect && Effect.getEffectType()
                    .equals(EffectTypes.SPELL) == false) {

                if (RandomUtils.roll(data.Value)) {
                    Effect.Number = 0;
                    Effect.isDodged = true;
                    Effect.canceled = true;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
