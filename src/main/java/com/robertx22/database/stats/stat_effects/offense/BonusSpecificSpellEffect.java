package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class BonusSpecificSpellEffect implements IStatEffect {

    public BonusSpecificSpellEffect(BaseSpell spell) {
        this.spell = spell;
    }

    public BaseSpell spell;

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
            if (Effect instanceof SpellDamageEffect) {

                SpellDamageEffect dmg = (SpellDamageEffect) Effect;

                if (dmg.spell.GUID().equals(spell.GUID())) {
                    dmg.number *= data.getMultiplier();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
