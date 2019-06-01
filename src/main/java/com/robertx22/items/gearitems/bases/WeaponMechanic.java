package com.robertx22.items.gearitems.bases;

import com.robertx22.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;

public abstract class WeaponMechanic {

    public abstract ITextComponent tooltipDesc();

    public abstract float GetEnergyCost();

    public abstract WeaponTypes weaponType();

    public boolean Attack(EntityLivingBase source, EntityLivingBase target,
                          UnitData unitsource, UnitData targetUnit) {

        int num = (int) unitsource.getUnit().MyStats.get(PhysicalDamage.GUID).Value;
        DamageEffect dmg = new DamageEffect(source, target, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK, weaponType());

        dmg.Activate();

        return true;
    }

    public boolean multiplyDamage(EntityLivingBase source, EntityLivingBase target,
                                  UnitData unitsource, UnitData targetUnit,
                                  float multiplier) {

        int num = (int) unitsource.getUnit().MyStats.get(PhysicalDamage.GUID).Value;
        DamageEffect dmg = new DamageEffect(source, target, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK, weaponType());

        dmg.setMultiplier(multiplier);

        dmg.Activate();

        return true;
    }

}
