package com.robertx22.database.stats.stat_effects.potion_effects;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class PotionBonusDmgEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    static float manaCost = 3F;

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                DamageEffect dmg = (DamageEffect) Effect;

                if (dmg.getEffectType() == EffectData.EffectTypes.BASIC_ATTACK) {

                    if (dmg.sourceData.hasEnoughMana(manaCost)) {
                        dmg.sourceData.consumeMana(manaCost);

                    } else {
                        return Effect;
                    }

                    float dmgNum = data.Value / dmg.damageMultiplier;
                    Elements element = stat.Element();
                    dmg.addBonusEleDmg(element, dmgNum);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
