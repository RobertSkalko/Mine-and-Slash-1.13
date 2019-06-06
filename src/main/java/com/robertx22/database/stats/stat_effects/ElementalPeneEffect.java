package com.robertx22.database.stats.stat_effects;

import com.robertx22.database.stats.Stat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.interfaces.IElementalEffect;
import com.robertx22.uncommon.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class ElementalPeneEffect implements IStatEffect {

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
            if (Effect instanceof IElementalPenetrable) {
                IElementalEffect ele = (IElementalEffect) Effect;

                if (ele.GetElement().equals(stat.Element()) || stat.Element()
                        .equals(Elements.Elemental)) {
                    IElementalPenetrable ipene = (IElementalPenetrable) Effect;
                    ipene.addElementalPenetration((int) data.Value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
