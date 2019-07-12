package com.robertx22.database.stats.stat_effects.potion_effects;

import com.robertx22.database.stats.Stat;
import com.robertx22.potion_effects.all.BonusDmgPotion;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import net.minecraft.potion.EffectInstance;

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

                if (dmg.getEffectType().equals(EffectData.EffectTypes.BASIC_ATTACK)) {

                    EffectInstance potion = BonusDmgPotion.getFirstEffect(dmg.source);

                    if (potion != null) {

                        if (dmg.sourceData.hasEnoughMana(manaCost)) {
                            dmg.sourceData.consumeMana(manaCost);

                        } else {
                            return Effect;
                        }

                        int dmgNum = potion.getAmplifier();
                        Elements element = ((BonusDmgPotion) potion.getPotion()).element;
                        dmg.addBonusEleDmg(element, dmgNum);

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
