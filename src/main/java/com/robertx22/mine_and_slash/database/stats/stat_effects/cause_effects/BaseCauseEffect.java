package com.robertx22.mine_and_slash.database.stats.stat_effects.cause_effects;

import com.robertx22.mine_and_slash.database.stats.stat_effects.OnCauseDoEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;

public abstract class BaseCauseEffect {

    public abstract void activate(OnCauseDoEffect oncause, EffectData effect);

}
