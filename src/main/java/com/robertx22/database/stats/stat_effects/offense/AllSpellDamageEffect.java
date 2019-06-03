package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class AllSpellDamageEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof SpellDamageEffect) {

                SpellDamageEffect dmgeffect = (SpellDamageEffect) Effect;

                if (dmgeffect.getEffectType().equals(EffectData.EffectTypes.SPELL)) {
                    dmgeffect.Number += data.Value * dmgeffect.spell.ScalingValue().Multi;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}