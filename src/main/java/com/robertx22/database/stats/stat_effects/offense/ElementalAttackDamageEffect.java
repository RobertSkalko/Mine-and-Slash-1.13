package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class ElementalAttackDamageEffect implements IStatEffect {

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
            if (Effect instanceof DamageEffect && Effect.getEffectType()
                    .equals(EffectTypes.BASIC_ATTACK)) {

                DamageEffect dmgeffect = (DamageEffect) Effect;

                if (dmgeffect.bonusElementDamageMap.containsKey(stat.Element())) {
                    dmgeffect.bonusElementDamageMap.put(stat.Element(), (int) (dmgeffect.bonusElementDamageMap
                            .get(stat.Element()) + data.Value));
                } else {
                    dmgeffect.bonusElementDamageMap.put(stat.Element(), (int) data.Value);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}