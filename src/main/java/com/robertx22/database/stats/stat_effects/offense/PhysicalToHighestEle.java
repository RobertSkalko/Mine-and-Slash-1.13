package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;
import net.minecraft.util.math.MathHelper;

public class PhysicalToHighestEle implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.Third.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                float val = Effect.number;
                DamageEffect dmg = (DamageEffect) Effect;

                if (dmg.element == Elements.Physical) {

                    Elements ele = dmg.getHighestBonusElementalDamageElement();

                    float multi = MathHelper.clamp(data.getMultiplier(), 0, 2);

                    float given = (val * multi) - val;

                    if (ele != null) {
                        dmg.addBonusEleDmg(ele, given);
                        dmg.number -= given;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}

