package com.robertx22.mine_and_slash.database.stats.stat_effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.ICrittable;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class CriticalHitEffect implements IStatEffect {

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
            if (Effect instanceof ICrittable) {

                ICrittable icrit = (ICrittable) Effect;

                if (RandomUtils.roll(data.Value)) {
                    icrit.SetCrit(true);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }
}
