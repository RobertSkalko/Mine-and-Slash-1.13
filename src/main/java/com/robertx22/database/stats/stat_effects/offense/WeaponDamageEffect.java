package com.robertx22.database.stats.stat_effects.offense;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.generated.WeaponDamage;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.interfaces.IStatEffect;

public class WeaponDamageEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return 0;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect && stat instanceof WeaponDamage) {

                WeaponDamage weapon = (WeaponDamage) stat;

                if (weapon.weaponType().equals(Effect.weaponType)) {
                    DamageEffect dmgeffect = (DamageEffect) Effect;
                    dmgeffect.Number *= 1 + data.Value / 100;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}