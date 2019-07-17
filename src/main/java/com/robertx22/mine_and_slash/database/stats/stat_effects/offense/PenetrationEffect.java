package com.robertx22.mine_and_slash.database.stats.stat_effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.defense.ArmorEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IPenetrable;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class PenetrationEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.beforeThis(new ArmorEffect().GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof IPenetrable) {
                IPenetrable ipene = (IPenetrable) Effect;
                ipene.SetArmorPenetration((int) data.Value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
