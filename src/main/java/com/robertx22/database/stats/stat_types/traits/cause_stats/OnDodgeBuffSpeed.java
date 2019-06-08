package com.robertx22.database.stats.stat_types.traits.cause_stats;

import com.robertx22.database.stats.stat_effects.OnCauseDoEffect;
import com.robertx22.database.stats.stat_effects.cause_effects.GivePotionEffect;
import com.robertx22.database.stats.stat_effects.causes.OnAttackDodgedCause;
import com.robertx22.database.stats.stat_types.BaseTrait;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;
import net.minecraft.potion.Effects;

import java.util.Arrays;
import java.util.List;

public class OnDodgeBuffSpeed extends BaseTrait implements IStatEffects {

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "On Dodge Buff Speed";
    }

    @Override
    public String locNameForLangFile() {
        return "Fleeting Wind";
    }

    @Override
    public String GUID() {
        return "fleeting_wind";
    }

    @Override
    public int Weight() {
        return 0;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new OnCauseDoEffect(new OnAttackDodgedCause(), 100, IStatEffect.EffectSides.Target, new GivePotionEffect(Effects.field_76424_c, 5), IStatEffect.EffectSides.Target));
    }
}
