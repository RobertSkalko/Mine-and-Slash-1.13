package com.robertx22.database.stats.stat_effects.defense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.UsableStat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.uncommon.effectdatas.interfaces.IElementalResistable;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class AllEleResistEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return 19;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof IElementalResistable) {

                int pene = 0;

                if (Effect instanceof IElementalPenetrable) {
                    IElementalPenetrable ipen = (IElementalPenetrable) Effect;
                    pene = ipen.GetElementalPenetration();
                }

                UsableStat resist = (UsableStat) stat;

                float EffectiveArmor = resist.GetUsableValue(Effect.targetData.getLevel(), (int) (data.Value - pene));

                if (EffectiveArmor < 0) {
                    EffectiveArmor = 0;
                }

                Effect.Number -= EffectiveArmor * Effect.Number;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
