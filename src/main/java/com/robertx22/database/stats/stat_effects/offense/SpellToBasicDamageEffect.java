package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.generated.ElementalSpellToAttackDMG;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class SpellToBasicDamageEffect implements IStatEffect {

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
                    .equals(EffectTypes.BASIC_ATTACK) && stat instanceof ElementalSpellToAttackDMG) {

                ElementalSpellToAttackDMG basebonus = (ElementalSpellToAttackDMG) stat;

                float percent = data.Value;
                float derivedvalue = (float) source.MyStats.get(basebonus.StatThatGiveDamage()
                        .GUID()).Value;

                int dmg = (int) (percent * derivedvalue / 100);

                DamageEffect dmgeffect = (DamageEffect) Effect;

                dmgeffect.addBonusEleDmg(stat.Element(), dmg);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
