package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class AllEleDmgEffectIfElement implements IStatEffect {

    @Override
    public int GetPriority() {
        return 15;
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

                DamageEffect dmgeffect = (DamageEffect) Effect;

                if (dmgeffect.Element.equals(stat.Element()) || stat.Element()
                        .equals(Elements.All)) {

                    dmgeffect.Number *= 1 + data.Value / 100;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}