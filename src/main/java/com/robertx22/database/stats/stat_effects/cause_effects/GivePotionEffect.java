package com.robertx22.database.stats.stat_effects.cause_effects;

import com.robertx22.database.stats.stat_effects.OnCauseDoEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.interfaces.IStatEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class GivePotionEffect extends BaseCauseEffect {

    private Potion potion;
    private int durationInSeconds;
    private int amplifier = 1;

    public GivePotionEffect(Potion potion, int durationInSeconds) {
        this.potion = potion;
        this.durationInSeconds = durationInSeconds;

    }

    public GivePotionEffect setAmplifier(int amp) {
        this.amplifier = amp;
        return this;
    }

    @Override
    public void activate(OnCauseDoEffect oncause, EffectData effect) {

        EntityLivingBase entity;

        if (oncause.whoGetsEffect.equals(IStatEffect.EffectSides.Source)) {
            entity = effect.source;

        } else {
            entity = effect.target;
        }

        entity.addPotionEffect(new PotionEffect(potion, durationInSeconds * 20, amplifier));

    }
}
