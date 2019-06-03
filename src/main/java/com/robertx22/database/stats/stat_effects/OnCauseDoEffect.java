package com.robertx22.database.stats.stat_effects;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.cause_effects.BaseCauseEffect;
import com.robertx22.database.stats.stat_effects.causes.BaseCause;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class OnCauseDoEffect implements IStatEffect {

    public OnCauseDoEffect(BaseCause cause, int chance, EffectSides whoGetsEffect,
                           BaseCauseEffect causeEffect) {
        this.cause = cause;
        this.chance = chance;
        this.whoGetsEffect = whoGetsEffect;
        this.causeEffect = causeEffect;

    }

    private BaseCauseEffect causeEffect;
    private BaseCause cause;
    private int chance;
    private EffectSides whoGetsEffect;

    @Override
    public int GetPriority() {
        return 100;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (RandomUtils.roll(chance)) {
                if (cause.shouldActivate(Effect)) {
                    causeEffect.activate(this, Effect);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}

